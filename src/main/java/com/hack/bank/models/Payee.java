package com.hack.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "payee", schema = "online_bank")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Payee {
    @Id   @GeneratedValue
    private  long id;
    String payeeName;
    private String accountNumber;
    private String bankName;

    @Override
    public String toString() {
        return "Payee{" +
                "id=" + id +
                ", payeeName='" + payeeName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payee payee = (Payee) o;
        return id == payee.id && Objects.equals(payeeName, payee.payeeName) && Objects.equals(accountNumber, payee.accountNumber) && Objects.equals(bankName, payee.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payeeName, accountNumber, bankName);
    }

}
