package com.hack.bank.repositories;

import com.hack.bank.models.Account;
import com.hack.bank.models.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
    Optional<Payee> findByPayeeName(String payeeName);
}
