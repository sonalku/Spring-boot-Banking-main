package com.hack.bank.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hack.bank.models.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByCardTypeAndAccountNumber(String cardType, String accountNumber);
    Optional<List<Cards>> findByAccountNumber(String accountNumber);
    List<Cards> findAll();
}
