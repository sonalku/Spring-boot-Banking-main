package com.hack.bank.repositories;

import com.hack.bank.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAll();
}
