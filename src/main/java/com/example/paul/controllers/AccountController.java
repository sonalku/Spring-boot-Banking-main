package com.example.paul.controllers;

import com.example.paul.constants.constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    //Show Transactions
    @GetMapping(value = "/account/{inputValue}")
    public ResponseEntity<List<String>> getAccountOption(
            @RequestParam(name = "inputValue", required = true) String inputValue
    ) {
        LOGGER.debug("Triggered AccountController.getAccountOption");
        switch (inputValue) {
            case constants.SHOW_TRANSACTION: return new ResponseEntity<List<String>>(constants.TRANSACTION_LIST, HttpStatus.OK);
            case constants.SHOW_BANALCE : return new ResponseEntity<List<String>>(constants.ACCOUNT_BALANCE,HttpStatus.OK);
            case constants.LAST_TRANSACTION : return new ResponseEntity<List<String>>(constants.TRANSACTIONS,HttpStatus.OK);
            default:
                return new ResponseEntity<List<String>>(constants.WRONG_INPUT_OPTIONS, HttpStatus.OK);
        }

    }
}