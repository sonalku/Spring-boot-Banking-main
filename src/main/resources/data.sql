INSERT INTO online_bank.account (id,account_type, ifsc_code, account_number, current_balance, bank_name, owner_name)
VALUES (1,'SAVINGS', '53-68-92', '73084635', 1071.78, 'Challenger Bank', 'Raj Patil');
INSERT INTO online_bank.account (id,account_type, ifsc_code, account_number, current_balance, bank_name, owner_name)
VALUES (2,'CURRENT', '65-93-37', '21956204', 67051.01, 'High Street Bank', 'Shyam McDuck');

INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (1, '21956204', 'Shyam McDuck', 100.00, '2019-04-01 10:30');

INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (2, '21956204', 'Shyam McDuck', 100.00, '2019-05-01 10:30');

INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (3, '73084635', 'Raj Patil', 10000.00, '2019-05-27 17:21');

INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (1, '73084635', 'CREDIT', '1111-2222-3333-2323', 20000, '2019-05-01 00:00', 50000);
INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (2, '73084635', 'DEBIT', '1111-2222-3333-1212', 35000, '2019-05-01 00:00', 1000);


INSERT INTO online_bank.payee(id,payee_name,account_number,ifsc_code,bank_name)
VALUES(1,'Pradeep J','123456','00-68-92','AXIS');
