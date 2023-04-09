CREATE SCHEMA online_bank;

CREATE TABLE online_bank.account (
    id bigint NOT NULL PRIMARY KEY,
    account_type CHAR(15) NOT NULL,
    ifsc_code CHAR(8) NOT NULL,
    account_number CHAR(8) NOT NULL,
    current_balance NUMERIC(10,3) NOT NULL,
    bank_name VARCHAR(50) NOT NULL,
    owner_name VARCHAR(50) NOT NULL,
    UNIQUE (ifsc_code, account_number)
);

CREATE TABLE online_bank.cards (
    id bigint NOT NULL PRIMARY KEY,
    account_number CHAR(8) NOT NULL,
	card_type VARCHAR(8) NOT NULL,
    current_balance NUMERIC(10,3) NOT NULL,
    card_number VARCHAR(50) NOT NULL,
    card_limit NUMERIC(10,3),
	billing_date timestamp NOT NULL
);

CREATE SEQUENCE online_bank.transaction_sequence START WITH 5;
CREATE TABLE online_bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    -- Partially denormalize for performance
    beneficiary_name varchar(50) NOT NULL,
    account_number CHAR(8) NOT NULL,
    amount NUMERIC(10,3) NOT NULL,
    transaction_date timestamp NOT NULL
);

CREATE TABLE online_bank.payee (
    id bigint NOT NULL PRIMARY KEY,
    payee_name CHAR(30) NOT NULL,
    account_number CHAR(8) NOT NULL,
    ifsc_code CHAR(8) NOT NULL,
    bank_name VARCHAR(50) NOT NULL
);
