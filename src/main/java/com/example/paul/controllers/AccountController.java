package com.example.paul.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

}
