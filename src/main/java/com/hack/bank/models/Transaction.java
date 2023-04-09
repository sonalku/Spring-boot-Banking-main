package com.hack.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction", schema = "online_bank")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", schema = "online_bank", initialValue = 5)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private long id;

    private long debitorAccountId;

    private long creditorAccountId;

    private String beneficiaryOwnerName;

    private double amount;

    private LocalDateTime transactionDate;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", debitorAccountId=" + debitorAccountId +
                ", creditorAccountId=" + creditorAccountId +
                ", beneficiaryOwnerName='" + beneficiaryOwnerName + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
