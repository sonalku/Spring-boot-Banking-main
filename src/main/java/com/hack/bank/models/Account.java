package com.hack.bank.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// TODO Add support for multiple account types (business, savings, etc.)
// TODO Add support for foreign currency accounts
@Entity
@Table(name = "account", schema = "online_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String accountType;

    private String ifscCode;

    private String accountNumber;

    private double currentBalance;

    private String bankName;

    private String ownerName;

    private String securityCode;

    private transient List<Transaction> transactions;
}
