package com.hack.bank.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountInput {

    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @NotBlank(message = "Owner name is mandatory")
    private String ownerName;

    @NotBlank(message = "Owner name is mandatory")
    private String securityCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountInput that = (CreateAccountInput) o;
        return Objects.equals(bankName, that.bankName) &&
                Objects.equals(ownerName, that.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, ownerName);
    }
}
