package com.example.paul.repositories;

import com.example.paul.models.Account;
import com.example.paul.models.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
    Optional<Payee> findByPayeeName(String payeeName);
}
