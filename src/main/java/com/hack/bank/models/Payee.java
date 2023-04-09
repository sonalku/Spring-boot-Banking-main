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
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    String payeeName;
    private String accountNumber;
    private String bankName;
    private String ifscCode;

}
