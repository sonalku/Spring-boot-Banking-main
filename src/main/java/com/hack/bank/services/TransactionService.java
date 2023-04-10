package com.hack.bank.services;

import com.hack.bank.constants.ACTION;
import com.hack.bank.models.Account;
import com.hack.bank.models.Transaction;
import com.hack.bank.repositories.AccountRepository;
import com.hack.bank.repositories.TransactionRepository;
import com.hack.bank.utils.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.hack.bank.constants.Constants.TRANSACTION_NOT_FOUND;

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

        if (sourceAccount.isPresent() && transactionInput.getSecurityCode().equalsIgnoreCase(sourceAccount.get().getSecurityCode())) {
            if (isAmountAvailable(transactionInput.getAmount(), sourceAccount.get().getCurrentBalance())) {
                var transaction = new Transaction();

                transaction.setAmount(transactionInput.getAmount());
                transaction.setBeneficiaryName(transactionInput.getBeneficiary());
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setAccountNumber(sourceAccountNumber);
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

    public Transaction getLastTransaction(String accountNumber) {
        Transaction lastTransaction = transactionRepository.findFirstByAccountNumberOrderByTransactionDateDesc(accountNumber).get();
        //findFirstByOrderByIdDesc
        return lastTransaction;
    }

    public ResponseEntity<List<String>> getTransactions(String accountNumber) {
        List<Transaction> lastTransactions = transactionRepository.findFirst10ByAccountNumberOrderByTransactionDateDesc(accountNumber);
        if (lastTransactions.isEmpty()) {
            return new ResponseEntity<>(List.of(TRANSACTION_NOT_FOUND), HttpStatus.OK);
        } else {
            List<String> transactions = new ArrayList<>();
            for (Iterator<Transaction> transaction = lastTransactions.iterator();
                 transaction.hasNext(); ) {
                Transaction trans = transaction.next();
                String transactionString = "TRANSACTION " + trans.getId() + " FOR AMOUNT " + trans.getAmount()
                        + ", CREDITED TO " + trans.getBeneficiaryName() + ". FROM ACCOUNT "
                        + trans.getAccountNumber() + ", ON " + convertToReadableDate(trans.getTransactionDate());
                transactions.add(transactionString);
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
    }
    private String convertToReadableDate(LocalDateTime localDateTime){
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(localDateTime.getDayOfWeek()).append(" ")
                .append(localDateTime.getDayOfMonth()).append(" ")
                .append(localDateTime.getMonth()).append(" ")
                .append(localDateTime.getYear()).append(" ")
                .append(", At ").append(" ")
                .append(localDateTime.getHour()).append(" ")
                .append(localDateTime.getMinute()).append(" ");


        return dateBuilder.toString();
    }
}

