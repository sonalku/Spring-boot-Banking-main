package com.example.paul.services;

import com.example.paul.constants.ACTION;
import com.example.paul.models.Account;
import com.example.paul.models.Transaction;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.utils.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean makeTransfer(TransactionInput transactionInput) {
        // TODO refactor synchronous implementation with messaging queue
        String sourceSortCode = transactionInput.getSourceAccount().getIfscCode();
        String sourceAccountNumber = transactionInput.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccount = accountRepository
                .findByIfscCodeAndAccountNumber(sourceSortCode, sourceAccountNumber);

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

     public void updateAccountBalance(Account account, double amount, ACTION action) {
        if (action == ACTION.WITHDRAW) {
            account.setCurrentBalance((account.getCurrentBalance() - amount));
        } else if (action == ACTION.DEPOSIT) {
            account.setCurrentBalance((account.getCurrentBalance() + amount));
        }
        accountRepository.save(account);
    }

    // TODO support overdrafts or credit account
    public boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
}
