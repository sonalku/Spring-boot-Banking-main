package com.hack.bank.integration;

import com.hack.bank.controllers.TransactionController;
import com.hack.bank.utils.AccountInput;
import com.hack.bank.utils.TransactionInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
/*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class MakeTransferIntegrationTest {

    @Autowired
    private TransactionController transactionController;

    @Test
    void givenTransactionDetails_whenMakeTransaction_thenVerifyTransactionIsProcessed() {
        // given
        var sourceAccount = new AccountInput();
        sourceAccount.setIfscCode("53-68-92");
        sourceAccount.setAccountNumber("73084635");

        var targetAccount = new AccountInput();
        targetAccount.setIfscCode("65-93-37");
        targetAccount.setAccountNumber("21956204");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(27.5);
        input.setReference("My reference");
        //input.setLatitude(45.0000000);
        //input.setLongitude(90.0000000);

        // when
        var body = transactionController.makeTransfer(input).getBody();

        // then
        var isComplete = (Boolean) body;
        assertThat(isComplete).isTrue();
    }
}
*/
