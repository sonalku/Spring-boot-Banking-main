INSERT INTO online_bank.account (id,account_type, ifsc_code, account_number, current_balance, bank_name, owner_name, security_code)
VALUES (1,'SAVINGS', '53-68-92', '73084635', 15000000, 'Easy Bank', 'John Lewis', 'I AM FINE');
INSERT INTO online_bank.account (id,account_type, ifsc_code, account_number, current_balance, bank_name, owner_name, security_code)
VALUES (2,'CURRENT', '65-93-37', '21956204', 250000000, 'Easy Bank', 'John Lewis', 'DO IT NOW');


INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (1, '21956204', 'John Lewis', 10000, '2019-04-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (2, '21956204', 'John Lewis', 125000, '2019-05-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (3, '73084635', 'John Lewis', 5000, '2019-05-27 17:21');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (4, '21956204', 'John Lewis', 4500, '2019-06-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (5, '21956204', 'John Lewis', 850000, '2019-06-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (6, '73084635', 'John Lewis', 103500, '2019-06-27 17:21');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (7, '21956204', 'John Lewis', 1009900, '2019-07-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (8, '21956204', 'John Lewis', 9900, '2019-05-07 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (9, '73084635', 'John Lewis', 8700, '2019-07-27 17:21');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (10, '21956204', 'John Lewis', 90780, '2019-08-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (11, '21956204', 'John Lewis', 75400, '2019-08-01 10:30');
INSERT INTO online_bank.transaction (id, account_number, beneficiary_name, amount, transaction_date )
VALUES (12, '73084635', 'John Lewis', 1000000, '2019-08-27 17:21');

INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (1, '73084635', 'CREDIT', '1111-2222-3333-2323', 200000, '2019-05-01 00:00', 500000);
INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (2, '73084635', 'DEBIT', '1111-2222-3333-1212', 350000, '2019-05-01 00:00', 9000000);

INSERT INTO online_bank.payee(id,payee_name,account_number,ifsc_code,bank_name)
VALUES(1,'Tom Brew','123456','00-68-92','Easy Bank');
