package com.hack.bank.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Constants {

    public static final String SUCCESS =
            "Operation completed successfully";
    public static final String NO_ACCOUNT_FOUND =
            "Unable to find an account matching this Ifsc code and account number";
    public static final String INVALID_SEARCH_CRITERIA =
            "The provided Ifsc code or account number did not match the expected format";

    public static final String INSUFFICIENT_ACCOUNT_BALANCE =
            "Your account does not have sufficient balance";

    public static final String IFSC_CODE_PATTERN_STRING = "[0-9]{2}-[0-9]{2}-[0-9]{2}";

    public static final String ACCOUNT_NUMBER_PATTERN_STRING = "[0-9]{8}";
    public static final Pattern IFSC_CODE_PATTERN = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{2}$");
    public static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[0-9]{8}$");

    public static final String INVALID_TRANSACTION =
            "Account information is invalid or transaction has been denied for your protection. Please try again.";
    public static final String CREATE_ACCOUNT_FAILED =
            "Error happened during creating new account";
    
    /********************************************/
    
    public static final String BANKING = "BANKING"; 
    public static final String OTHER_SERVICES = "OTHER SERVICES"; 
    public static final String STATEMENT = "STATEMENT"; 
    public static final String CARDS = "CARDS"; 
    public static final String ACCOUNT = "ACCOUNT";
    
    public static final String SHOW_TRANSACTION = "SHOW TRANSACTION";
	public static final String CHECK_BALANCE = "CHECK BALANCE";
	public static final String LAST_TRANSACTION = "LAST TRANSACTION";
	public static final String CARD_LIMIT = "CARD LIMIT";
	public static final String PENDING_BILL = "PENDING BILL";
	public static final String SEND_MONEY = "SEND MONEY";
	
	public static final String CHANGE_ADDRESS = "CHANGE ADDRESS";
	public static final String CHECK_STATUS = "CHECK STATUS";
	public static final String REQUEST_CHECK_BOOK = "REQUEST CHECKBOOK";
	public static final String BLOCK_CARD = "BLOCK CARD";
	public static final String CHANGE_PIN = "CHANGE PIN";
	
	public static final String SHOW_BALANCE = "SHOW BALANCE";
	
	public static final String GET_STATEMENT = "GET STATEMENT";
	
	public static final String WRONG_INPUT_OPTIONS_MESSAGE = "PLEASE INPUT CORRECT CHOICE FROM GIVEN OPTIONS";
    public static final String TRANSACTION_1 = "TRANSACTION ID 012 FOR AMOUNT 100 HAS BEEN CREDITED TO ACCOUNT 57778952 FROM DEBITED ACCOUNT 12345678 ON 6th APRIL";
    public static final String TRANSACTION_2 = "TRANSACTION ID 101 FOR AMOUNT 500 HAS BEEN CREDITED TO ACCOUNT 99881088 FROM DEBITED ACCOUNT 12345678 ON 5th APRIL";
    public static final String TRANSACTION_3 = "TRANSACTION ID 101 FOR AMOUNT 200 HAS BEEN CREDITED TO ACCOUNT 99881088 FROM DEBITED ACCOUNT 12345678 ON 15th May";

    public static final String BALANCE = "ACCOUNT HAS BALANCE OF 3,00,000,00";

    public static final List<String> OPTIONS = new ArrayList<String>(Arrays.asList(OTHER_SERVICES,STATEMENT,CARDS,ACCOUNT));
    public static final List<String> ACCOUNT_OPTIONS = new ArrayList<String>(Arrays.asList(SHOW_BALANCE,SHOW_TRANSACTION,CHECK_BALANCE,LAST_TRANSACTION,SEND_MONEY));
    public static final List<String> OTHER_SERVICES_OPTIONS = new ArrayList<String>(Arrays.asList(CHANGE_ADDRESS,CHECK_STATUS,REQUEST_CHECK_BOOK,BLOCK_CARD,CHANGE_PIN));
    public static final List<String> STATEMENTS_OPTIONS = new ArrayList<String>(Arrays.asList(GET_STATEMENT));
    public static final List<String> CARDS_OPTIONS = new ArrayList<String>(Arrays.asList(CARD_LIMIT,PENDING_BILL));
    //public static final List<String> ACCOUNT_OPTIONS = new ArrayList<String>(Arrays.asList(SHOW_BANALCE));
    public static final List<String> WRONG_INPUT_OPTIONS = new ArrayList<String>(Arrays.asList(WRONG_INPUT_OPTIONS_MESSAGE));

    //SHOW_TRANSACTION --> Will show list of transactions i.e TRANSACTION_LIST
    public static final List<String> TRANSACTION_LIST = new ArrayList<String>(Arrays.asList(TRANSACTION_1,TRANSACTION_2,TRANSACTION_3));

    //SHOW_BANALCE --> Will show account balance
    public static final List<String> ACCOUNT_BALANCE = new ArrayList<String>(Arrays.asList(BALANCE));

    public static final String TRANSACTION = "YOUR TRANSACTION ID 101 IS FOR AMOUNT 200 WHICH WAS CREDITED TO ACCOUNT 99881088 FROM DEBITED ACCOUNT 12345678 ON 15th May";
    //
    public static final List<String> TRANSACTIONS = new ArrayList<String>(Arrays.asList(TRANSACTION));

}
