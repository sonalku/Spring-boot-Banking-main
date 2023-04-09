package com.hack.bank.repositories;

import com.hack.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(String accountNumber);
    Optional<Transaction> findFirstByAccountNumberOrderByTransactionDateDesc(String accountNumber);

    List<Transaction> findFirst10ByAccountNumberOrderByTransactionDateDesc(String accountNumber);
}
