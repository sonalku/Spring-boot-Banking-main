package com.hack.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "type", schema = "online_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String typeCode;
}
