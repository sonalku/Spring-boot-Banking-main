package com.hack.bank.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards", schema = "online_bank")
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String accountNumber;
	
	private String cardType;
	
	private String cardNumber;
	
	private double currentBalance;
	
	private LocalDateTime billingDate;
	
	private double cardLimit;

}
