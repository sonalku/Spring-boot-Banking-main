package com.hack.bank.utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInput {

    private AccountInput sourceAccount;

    private String securityCode;

    private String beneficiary;

    @Positive(message = "Transfer amount must be positive")
    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Min(value = 1, message = "Amount must be larger than 1")
    private double amount;

    private String reference;
}
