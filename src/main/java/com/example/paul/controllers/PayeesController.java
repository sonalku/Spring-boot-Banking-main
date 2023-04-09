package com.example.paul.controllers;

import com.example.paul.models.Payee;
import com.example.paul.services.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class PayeesController {
    @Autowired
    private PayeeService payeeService;
    @GetMapping(value = "payees")
    public ResponseEntity<List<Payee>> getPayees() {
        List<Payee>  payees = payeeService.getAllPayees();
        return new ResponseEntity<>(payees, HttpStatus.OK);
    }

    @GetMapping(value = "payees/{payeeName}")
    public ResponseEntity<Optional<Payee>> getPayeeByName(
            @RequestParam(name = "payeeName", required = true) String payeeName){
        Optional<Payee> payee = payeeService.getPayeeByName(payeeName.trim());
        return new ResponseEntity<>(payee, HttpStatus.OK);
    }

}
