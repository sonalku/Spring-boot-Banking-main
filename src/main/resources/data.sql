INSERT INTO online_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (1, '53-68-92', '73084635', 1071.78, 'Challenger Bank', 'Paul Dragoslav');
INSERT INTO online_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (2, '65-93-37', '21956204', 67051.01, 'High Street Bank', 'Scrooge McDuck');

INSERT INTO online_bank.transaction (id, debitor_account_id, creditor_account_id, beneficiary_owner_name, amount, transaction_date )
VALUES (1, 1, 2, 'Scrooge McDuck', 100.00, '2019-04-01 10:30');

INSERT INTO online_bank.transaction (id, debitor_account_id, creditor_account_id, beneficiary_owner_name, amount, transaction_date)
VALUES (2, 1, 2, 'Scrooge McDuck', 100.00, '2019-05-01 10:30');

INSERT INTO online_bank.transaction (id, debitor_account_id, creditor_account_id, beneficiary_owner_name, amount, transaction_date)
VALUES (3, 2, 1, 'Paul Dragoslav', 10000.00, '2019-05-27 17:21');

INSERT INTO online_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date, completion_date, reference)
VALUES (3, 2, 1, 'Paul Dragoslav', 10000.00, '2019-05-27 17:21', null, 'Ha Ha I am rich');

INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (1, '53-68-92', 'CREDIT', '1111-2222-3333-2323', 20000, '2019-05-01 00:00', 50000);
INSERT INTO online_bank.cards (id, account_number, card_Type, card_Number, current_Balance, billing_Date, card_Limit)
VALUES (2, '53-68-92', 'DEBIT', '1111-2222-3333-1212', 35000, '2019-05-01 00:00', null);


INSERT INTO online_bank.payee(id,payee_name,account_number,bank_name)
VALUES(1,'Pradeep J','123456','AXIS');
