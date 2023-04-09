package com.hack.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

// TODO Add support for multiple account types (business, savings, etc.)
// TODO Add support for foreign currency accounts
@Entity
@Table(name = "account", schema = "online_bank")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private long id;

    private String ifscCode;

    private String accountNumber;

    private double currentBalance;

    private String bankName;

    private String ownerName;

    private transient List<Transaction> transactions;

    public Account(String bankName, String ownerName, String generateSortCode, String generateAccountNumber, double currentBalance) {
        this.ifscCode = generateSortCode;
        this.accountNumber = generateAccountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.ownerName = ownerName;
    }
    public Account(long id, String ifscCode, String accountNumber, double currentBalance, String bankName, String ownerName) {
        this.id = id;
        this.ifscCode = ifscCode;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ifscCode='" + ifscCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", bankName='" + bankName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
