package com.hack.bank.repositories;

import com.hack.bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIfscCodeAndAccountNumber(String ifscCode, String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
}
