package com.hack.bank.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payee", schema = "online_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "payee_seq", sequenceName = "payee_sequence", schema = "online_bank", allocationSize = 1)
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payee_seq")
    private  long id;
    String payeeName;
    private String accountNumber;
    private String bankName;
    private String ifscCode;

}
