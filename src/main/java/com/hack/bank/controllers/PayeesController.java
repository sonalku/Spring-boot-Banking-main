package com.hack.bank.controllers;

import com.hack.bank.models.Payee;
import com.hack.bank.services.PayeeService;
import com.hack.bank.utils.CreateAccountInput;
import com.hack.bank.utils.PayeeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/payees")
@CrossOrigin(origins = "http://localhost:4200")
public class PayeesController {
    @Autowired
    private PayeeService payeeService;
    @GetMapping
    public ResponseEntity<List<Payee>> getPayees() {
        List<Payee>  payees = payeeService.getAllPayees();
        return new ResponseEntity<>(payees, HttpStatus.OK);
    }

    @GetMapping(value = "/{payeeName}")
    public ResponseEntity<Optional<Payee>> getPayeeByName(
            @RequestParam(name = "payeeName", required = true) String payeeName){
        Optional<Payee> payee = payeeService.getPayeeByName(payeeName.trim());
        return new ResponseEntity<>(payee, HttpStatus.OK);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPayee(
            @Valid @RequestBody PayeeInput payee) {
        return payeeService.addPayee(payee);
    }


}
