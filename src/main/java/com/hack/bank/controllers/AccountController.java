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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hack.bank.constants.Constants.INVALID_TRANSACTION;
import static com.hack.bank.constants.Constants.SUCCESSFUL_TRANSACTION;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            @Valid @RequestBody AccountInput accountInput) {
        LOGGER.debug("Triggered AccountRestController.accountInput");

        if (InputValidator.isSearchCriteriaValid(accountInput)) {
            Account account = accountService.getAccount(accountInput.getAccountNumber());
            if (account == null) {
                return new ResponseEntity<>(Constants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account.getCurrentBalance(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountInput createAccountInput) {
        LOGGER.debug("Triggered AccountRestController.createAccountInput");

        // Validate input
        if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
            // Attempt to retrieve the account information
            Account account = accountService.createAccount(
                    createAccountInput.getBankName(),
                    createAccountInput.getOwnerName(),
                    createAccountInput.getSecurityCode());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(Constants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
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

    @GetMapping(value = "/account/balance/")
    public ResponseEntity<List<String>> showAccountBalance(
            @RequestParam(name = "accountNumber", required = true) String accountNumber
    ){
            Account account = accountService.getAccount(accountNumber);
            ArrayList<String> list = new ArrayList<>();

            if(null != account) {
                list.add("Your Account Balance is "+account.getCurrentBalance());
                return new ResponseEntity<List<String>>(list, HttpStatus.OK);
            }
            else {
                list.add("Account Not found");
                return new ResponseEntity<List<String>>(list, HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping(value = "/account/sendMoney/{accountNumber}/{amount}/{payee}")
    public ResponseEntity<List<String>> sendMoney(
            @RequestParam(name = "accountNumber", required = true) String accountNumber,
            @RequestParam(name = "amount", required = true) double amount,
            @RequestParam(name = "payee", required = true) String payee,
            @RequestParam(name = "securityCode", required = true) String securityCode
    ) {
        LOGGER.debug("Triggered AccountController.getAccountOption");
        ArrayList<String> list = new ArrayList();
        String output = accountService.sentMoney(accountNumber, amount, payee,securityCode);
        list.add(output);

        if(output.equalsIgnoreCase(SUCCESSFUL_TRANSACTION))
            return new ResponseEntity<List<String>>(list,HttpStatus.OK);
        else
            return new ResponseEntity<List<String>>(list,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/account/sendMoney")
    public ResponseEntity<List<String>> makeTransfer(
            @Valid @RequestBody TransactionInput transactionInput) {
        ArrayList<String> output = new ArrayList();
        if (InputValidator.isSearchTransactionValid(transactionInput)) {
            boolean isComplete = accountService.makeTransfer(transactionInput);
            output.add(SUCCESSFUL_TRANSACTION);
            return new ResponseEntity<List<String>>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<String>>(output, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/account/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts,HttpStatus.OK);

    }

}