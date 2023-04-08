package com.example.paul.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction", schema = "online_bank")

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

    public Transaction() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDebitorAccountId() {
        return debitorAccountId;
    }

    public void setDebitorAccountId(long debitorAccountId) {
        this.debitorAccountId = debitorAccountId;
    }

    public long getCreditorAccountId() {
        return creditorAccountId;
    }

    public void setCreditorAccountId(long creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
    }

    public String getBeneficiaryOwnerName() {
        return beneficiaryOwnerName;
    }

    public void setBeneficiaryOwnerName(String beneficiaryOwnerName) {
        this.beneficiaryOwnerName = beneficiaryOwnerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

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
