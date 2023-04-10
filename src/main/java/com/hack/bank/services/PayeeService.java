package com.hack.bank.services;

import com.hack.bank.models.Account;
import com.hack.bank.models.Payee;
import com.hack.bank.repositories.PayeeRepository;
import com.hack.bank.utils.PayeeInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Optional<Payee> payee = payeeRepository.findByPayeeNameLike("%"+payeeName+"%");
        return payee;
    }

    public ResponseEntity<?> addPayee(PayeeInput payee) {
        Payee newPayee = new Payee();
        newPayee.setPayeeName(payee.getPayeeName());
        newPayee.setBankName(payee.getBankName());
        newPayee.setAccountNumber(payee.getAccountNumber());
        newPayee.setIfscCode(payee.getIfscCode());
        return new ResponseEntity<Payee>(payeeRepository.save(newPayee), HttpStatus.CREATED);
    }
}
