package com.hack.bank.controllers;

import com.hack.bank.constants.Constants;
import com.hack.bank.models.Account;
import com.hack.bank.services.TypeService;
import com.hack.bank.utils.AccountInput;
import com.hack.bank.utils.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/type")
@CrossOrigin(origins = "http://localhost:4200")
public class TypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;
    @GetMapping()
    public ResponseEntity<List<String>> getType() {
        LOGGER.debug("Triggered AccountRestController.accountInput");
        List<String> output = new ArrayList();
        output.add(typeService.getType());
        return new ResponseEntity<List<String>>(output,HttpStatus.OK);
    }
    @PostMapping()
    public void setType(
            @RequestParam(name = "typeCode", required = true) String typeCode
    ) {
        LOGGER.debug("Triggered AccountRestController.accountInput");
         typeService.setType(typeCode);
    }
}
