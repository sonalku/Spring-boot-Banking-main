package com.example.paul.services;

import com.example.paul.constants.ACTION;
import com.example.paul.models.Account;
import com.example.paul.models.Transaction;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.utils.CodeGenerator;
import com.example.paul.utils.TransactionInput;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(String sortCode, String accountNumber) {
        Optional<Account> account = accountRepository
                .findByIfscCodeAndAccountNumber(sortCode, accountNumber);

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findByDebitorAccountId(value.getId())));

        return account.orElse(null);
    }

    public Account getAccount(String accountNumber) {
        Optional<Account> account = accountRepository
                .findByAccountNumber(accountNumber);

        return account.orElse(null);
    }

    public Account createAccount(String bankName, String ownerName) {
        CodeGenerator codeGenerator = new CodeGenerator();
        Account newAccount = new Account(bankName, ownerName, codeGenerator.generateSortCode(), codeGenerator.generateAccountNumber(), 0.00);
        return accountRepository.save(newAccount);
    }

    public ResponseEntity<String> sentMoney(String accountNumber, double amount, String payee) {
        // TODO Auto-generated method stub

        Account myAccount = accountRepository.findByAccountNumber(accountNumber).get();

        if(myAccount.getCurrentBalance() > amount) {

            double remaiiningBalance = myAccount.getCurrentBalance() - amount;
            myAccount.setCurrentBalance(remaiiningBalance);
            accountRepository.save(myAccount);

            var transaction = new Transaction();

            transaction.setAmount(amount);
            transaction.setDebitorAccountId(1);
            transaction.setCreditorAccountId(2);
            transaction.setBeneficiaryOwnerName(payee);
            transaction.setTransactionDate(LocalDateTime.now());

            //updateAccountBalance(sourceAccount.get(), transactionInput.getAmount(), ACTION.WITHDRAW);
            transactionRepository.save(transaction);

            return new ResponseEntity<String>("Amount sent succesfully",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Insufficient Balance",HttpStatus.NOT_FOUND);
    }

    public boolean makeTransfer(@Valid TransactionInput transactionInput) {
        // TODO Auto-generated method stub
        String ifscCode = transactionInput.getSourceAccount().getIfscCode();
        String sourceAccountNumber = transactionInput.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccount = accountRepository
                .findByIfscCodeAndAccountNumber(ifscCode, sourceAccountNumber);

        String targetSortCode = transactionInput.getTargetAccount().getIfscCode();
        String targetAccountNumber = transactionInput.getTargetAccount().getAccountNumber();
        Optional<Account> targetAccount = accountRepository
                .findByIfscCodeAndAccountNumber(targetSortCode, targetAccountNumber);

        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
            if (isAmountAvailable(transactionInput.getAmount(), sourceAccount.get().getCurrentBalance())) {
                var transaction = new Transaction();

                transaction.setAmount(transactionInput.getAmount());
                transaction.setDebitorAccountId(sourceAccount.get().getId());
                transaction.setCreditorAccountId(targetAccount.get().getId());
                transaction.setBeneficiaryOwnerName(targetAccount.get().getOwnerName());
                transaction.setTransactionDate(LocalDateTime.now());

                updateAccountBalance(sourceAccount.get(), transactionInput.getAmount(), ACTION.WITHDRAW);
                transactionRepository.save(transaction);

                return true;
            }
        }
        return false;
    }
    public boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
    public void updateAccountBalance(Account account, double amount, ACTION action) {
        if (action == ACTION.WITHDRAW) {
            account.setCurrentBalance((account.getCurrentBalance() - amount));
        } else if (action == ACTION.DEPOSIT) {
            account.setCurrentBalance((account.getCurrentBalance() + amount));
        }
        accountRepository.save(account);
    }

}
