package com.hack.bank.utils;

import com.mifmif.common.regex.Generex;

import static com.hack.bank.constants.Constants.ACCOUNT_NUMBER_PATTERN_STRING;
import static com.hack.bank.constants.Constants.IFSC_CODE_PATTERN_STRING;

public class CodeGenerator {
    Generex sortCodeGenerex = new Generex(IFSC_CODE_PATTERN_STRING);
    Generex accountNumberGenerex = new Generex(ACCOUNT_NUMBER_PATTERN_STRING);

    public CodeGenerator(){}

    public String generateSortCode() {
        return sortCodeGenerex.random();
    }

    public String generateAccountNumber() {
        return accountNumberGenerex.random();
    }
}
