package com.hack.bank.services;

import com.hack.bank.models.Account;
import com.hack.bank.models.Payee;
import com.hack.bank.repositories.PayeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayeeService {
    private final PayeeRepository payeeRepository;

    public PayeeService(PayeeRepository payeeRepository) {
        this.payeeRepository = payeeRepository;
    }

    public List<Payee> getAllPayees(){
        List<Payee> payees = payeeRepository.findAll();
        return payees;
    }

    public Optional<Payee> getPayeeByName(String payeeName){
        Optional<Payee> payee = payeeRepository.findByPayeeName(payeeName);
        return payee;
    }
}
