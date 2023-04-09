package com.hack.bank.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AccountInput {

    @NotBlank(message = "Ifsc code is mandatory")
    private String ifscCode;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;


    @Override
    public String toString() {
        return "AccountInput{" +
                "ifscCode='" + ifscCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInput that = (AccountInput) o;
        return Objects.equals(ifscCode, that.ifscCode) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifscCode, accountNumber);
    }
}
