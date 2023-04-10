package com.hack.bank.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayeeInput {
    String payeeName;
    private String accountNumber;
    private String bankName;
    private String ifscCode;
}
