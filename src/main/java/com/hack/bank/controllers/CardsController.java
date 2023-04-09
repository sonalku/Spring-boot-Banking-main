package com.hack.bank.controllers;

import com.hack.bank.models.Cards;
import com.hack.bank.repositories.CardsRepository;
import com.hack.bank.services.CardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class CardsController {
	@Autowired
	CardsRepository cardsRepository;

	@Autowired
	CardsService cardsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);

	@GetMapping()
    public ResponseEntity<List<Cards>> getCards() {
        LOGGER.debug("Triggered CardsController.accountInput");
        List<Cards> cards = cardsRepository.findAll();
		return new ResponseEntity<List<Cards>>(cards,HttpStatus.OK);
    }

	@GetMapping(value = "/options/{accountNumber}")
    public ResponseEntity<List<String>> getOptions(
			@RequestParam(name = "accountNumber", required = true) String inputValue
	) {
        LOGGER.debug("Triggered CardsController.accountInput");
		return null;

	}

	@GetMapping(value = "/card/{accountNumber}")
	public ResponseEntity<List<String>> getCardLimitByAccountNumber(
			@RequestParam(name = "accountNumber", required = true) String accountNumber){
		Optional<List<Cards>> cards = cardsService.getCardByAccountNumber(accountNumber);
		if(cards.get().isEmpty()){
			return new ResponseEntity<>(List.of("No Cards Found against account Number"),HttpStatus.NOT_FOUND);
		}else{
			List<String> availableCards = new ArrayList<>();
			for(Cards card: cards.get()){
				String cardDetail = " Card Number is " + card.getCardNumber() + " Card Limit is " + card.getCardLimit();
				availableCards.add(cardDetail);
			}
			return new ResponseEntity<>(availableCards,HttpStatus.OK);
		}
	}

}
