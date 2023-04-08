package com.example.paul.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.paul.constants.constants;

@RestController
@RequestMapping("api/v1/bank")
@CrossOrigin(origins = "http://localhost:3000")
public class BankingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BankingController.class);
	
	@GetMapping()
    public ResponseEntity<List<String>> getOptions() {
        LOGGER.debug("Triggered AccountRestController.accountInput");     
		return new ResponseEntity<List<String>>(constants.OPTIONS,HttpStatus.OK);
    }
	
	@GetMapping(value = "/options/{inputValue}")
    public ResponseEntity<List<String>> getOptions(
    		@RequestParam(name = "inputValue", required = true) String inputValue
    		) {
        LOGGER.debug("Triggered AccountRestController.accountInput");  
        switch (inputValue) {
		case constants.OTHER_SERVICES : return new ResponseEntity<List<String>>(constants.OTHER_SERVICES_OPTIONS,HttpStatus.OK);
		case constants.STATEMENT : return new ResponseEntity<List<String>>(constants.STATEMENTS_OPTIONS,HttpStatus.OK);
		case constants.CARDS : return new ResponseEntity<List<String>>(constants.CARDS_OPTIONS,HttpStatus.OK);
		case constants.ACCOUNT : return new ResponseEntity<List<String>>(constants.ACCOUNT_OPTIONS,HttpStatus.OK);
		default:
			return new ResponseEntity<List<String>>(constants.WRONG_INPUT_OPTIONS,HttpStatus.OK);
		}
		
    }

}
