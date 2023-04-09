package com.hack.bank.controllers;

import com.hack.bank.constants.Constants;
import com.hack.bank.models.Account;
import com.hack.bank.services.AccountService;
import com.hack.bank.utils.InputValidator;
import com.hack.bank.utils.TransactionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hack.bank.constants.Constants.INVALID_TRANSACTION;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

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