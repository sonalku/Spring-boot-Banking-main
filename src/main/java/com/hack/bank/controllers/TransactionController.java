package com.hack.bank.controllers;

import com.hack.bank.constants.ACTION;
import com.hack.bank.constants.Constants;
import com.hack.bank.models.Account;
import com.hack.bank.models.Transaction;
import com.hack.bank.services.AccountService;
import com.hack.bank.services.TransactionService;
import com.hack.bank.services.TypeService;
import com.hack.bank.utils.DepositInput;
import com.hack.bank.utils.InputValidator;
import com.hack.bank.utils.TransactionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static com.hack.bank.constants.Constants.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final AccountService accountService;
    private final TransactionService transactionService;
    @Autowired
    private TypeService typeService;

    @Autowired
    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransfer(
            @Valid @RequestBody TransactionInput transactionInput) {
        if (InputValidator.isSearchTransactionValid(transactionInput)) {
            boolean isComplete = transactionService.makeTransfer(transactionInput);
            return new ResponseEntity<>(isComplete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deposit(
            @Valid @RequestBody DepositInput depositInput) {
        LOGGER.debug("Triggered AccountRestController.depositInput");

        // Validate input
        if (InputValidator.isAccountNoValid(depositInput.getTargetAccountNo())) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(depositInput.getTargetAccountNo());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                transactionService.updateAccountBalance(account, depositInput.getAmount(), ACTION.DEPOSIT);
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @GetMapping(value = "/account/getLastTransaction")
    public ResponseEntity<Transaction> getLastTransaction(
            @RequestParam(name = "accountNumber", required = true) String accountNumber){
        return new ResponseEntity<Transaction>(transactionService.getLastTransaction(accountNumber),HttpStatus.OK);
    }
    @GetMapping(value = "/account/lastTransactionForVoive")
    public ResponseEntity<List<String>> lastTransactionForVoive(
            @RequestParam(name = "accountNumber", required = true) String accountNumber){
            Transaction lastTransaction = transactionService.getLastTransaction(accountNumber);
            StringBuilder builder = new StringBuilder();

            var redableDate = convertToReadableDate(lastTransaction.getTransactionDate());
            builder.append("Your Last Transaction is Transaction Number ")
                    .append(lastTransaction.getId())
                    .append(".")
                    .append(" Debited From Account Number ")
                    .append(lastTransaction.getAccountNumber())
                    .append(", Amount ")
                    .append(lastTransaction.getAmount())
                    .append(" To Beneficiary ")
                    .append(lastTransaction.getBeneficiaryName())
                    .append(", On ")
                    .append(redableDate);
        ArrayList<String> list = new ArrayList<>();
        list.add(builder.toString());
            return new ResponseEntity<List<String>>(list,HttpStatus.OK);
    }

    @GetMapping(value = "/account/transactions")
    public ResponseEntity<List<String>> getTransactions(
            @RequestParam(name = "accountNumber", required = true) String accountNumber){
                return transactionService.getTransactions(accountNumber);
    }

    @GetMapping(value = "/showStatistics")
    public ResponseEntity<List<String>> showStatistics(String accountNumber){
        List<String> list = new ArrayList();
        list.add("Your Have Spent 34 % on Rent.");
        list.add("Your Have Spent 12 % on Shopping.");
        list.add("Your Have Spent 22 % on Grocery.");
        list.add("Your Have Spent 16 % on Bill Payment.");
        list.add("Your Have Spent 16 % on Other Expenses.");
        return new ResponseEntity<List<String>>(list,HttpStatus.OK);
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
