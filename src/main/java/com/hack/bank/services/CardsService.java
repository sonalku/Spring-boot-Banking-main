package com.hack.bank.services;

import com.hack.bank.models.Cards;
import com.hack.bank.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardsService {
    @Autowired
    private CardsRepository cardsRepository;

    public Optional<List<Cards>> getCardByAccountNumber(String accountNumber){
        Optional<List<Cards>> cards = cardsRepository.findByAccountNumber(accountNumber);
        return cards;

    }

}
