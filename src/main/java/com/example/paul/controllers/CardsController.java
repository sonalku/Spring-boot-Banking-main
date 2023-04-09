package com.example.paul.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.paul.models.Cards;
import com.example.paul.repositories.CardRepository;

@RestController
@RequestMapping("api/v1/bank/cards")
public class CardsController {
	@Autowired
	CardRepository cardRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);
	
	@GetMapping()
    public ResponseEntity<List<Cards>> getCrads() {
        LOGGER.debug("Triggered CardsController.accountInput");
        List<Cards> cards = cardRepository.findAll();
		return new ResponseEntity<List<Cards>>(cards,HttpStatus.OK);
    }
	
	@GetMapping(value = "/options/{accountNumber}")
    public ResponseEntity<List<String>> getOptions(
    		@RequestParam(name = "accountNumber", required = true) String inputValue
    		) {
        LOGGER.debug("Triggered CardsController.accountInput");
		return null;  
        
		
    }

}
