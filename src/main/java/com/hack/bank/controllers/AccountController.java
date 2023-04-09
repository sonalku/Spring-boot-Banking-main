package com.hack.bank.controllers;

import com.hack.bank.constants.Constants;
import com.hack.bank.models.Account;
import com.hack.bank.models.Transaction;
import com.hack.bank.services.AccountService;
import com.hack.bank.utils.AccountInput;
import com.hack.bank.utils.CreateAccountInput;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hack.bank.constants.Constants.INVALID_TRANSACTION;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            // TODO In the future support searching by card number in addition to sort code and account number
            @Valid @RequestBody AccountInput accountInput) {
        LOGGER.debug("Triggered AccountRestController.accountInput");

        // Validate input
        if (InputValidator.isSearchCriteriaValid(accountInput)) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(accountInput.getAccountNumber());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(Constants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


   /* @PutMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountInput createAccountInput) {
        LOGGER.debug("Triggered AccountRestController.createAccountInput");

        // Validate input
        if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
            // Attempt to retrieve the account information
            Account account = accountService.createAccount(
                    createAccountInput.getBankName(), createAccountInput.getOwnerName());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(Constants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }*/

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

    //Show Transactions
    @GetMapping(value = "/account/{inputValue}")
    public ResponseEntity<List<String>> getAccountOption(
            @RequestParam(name = "inputValue", required = true) String inputValue
    ) {
        LOGGER.debug("Triggered AccountController.getAccountOption");
        switch (inputValue) {
            case Constants.SHOW_TRANSACTION: return new ResponseEntity<List<String>>(Constants.TRANSACTION_LIST, HttpStatus.OK);
            case Constants.SHOW_BALANCE: return new ResponseEntity<List<String>>(Constants.ACCOUNT_BALANCE,HttpStatus.OK);
            case Constants.LAST_TRANSACTION : return new ResponseEntity<List<String>>(Constants.TRANSACTIONS,HttpStatus.OK);
            default:
                return new ResponseEntity<List<String>>(Constants.WRONG_INPUT_OPTIONS, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/account/balance/")
    public ResponseEntity<Double> showAccountBalance(
            @RequestParam(name = "accountNumber", required = true) String accountNumber,
            @RequestParam(name = "operation", required = true) String operation
    ){
        if(Constants.SHOW_BALANCE.equals(operation)){
            Account account = accountService.getAccount(accountNumber);
            return new ResponseEntity<Double>(account.getCurrentBalance(),HttpStatus.OK);
        }
        return null;
    }

    @GetMapping(value = "/account/sendMoney/{accountNumber}/{amount}/{payee}")
    public ResponseEntity<String> sendMoney(
            @RequestParam(name = "accountNumber", required = true) String accountNumber,
            @RequestParam(name = "amount", required = true) double amount,
            @RequestParam(name = "payee", required = true) String payee
    ) {
        LOGGER.debug("Triggered AccountController.getAccountOption");
        return accountService.sentMoney(accountNumber, amount, payee);
    }

    @PostMapping(value = "/account/sendMoney",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransfer(
            @Valid @RequestBody TransactionInput transactionInput) {
        if (InputValidator.isSearchTransactionValid(transactionInput)) {
//            new Thread(() -> transactionService.makeTransfer(transactionInput));
            boolean isComplete = accountService.makeTransfer(transactionInput);
            return new ResponseEntity<>(isComplete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }

}