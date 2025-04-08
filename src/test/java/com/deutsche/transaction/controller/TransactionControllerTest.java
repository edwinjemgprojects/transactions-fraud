package com.deutsche.transaction.controller;

import com.deutsche.transaction.entities.Transaction;
import com.deutsche.transaction.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;  //
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.swing.text.html.Option;

import static org.hamcrest.CoreMatchers.is;

//import java.awt.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Optional.*;

@WebMvcTest
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    @MockitoBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("Save transaction and apply rule")
    void testSaveTransaction() throws Exception{
        Transaction trx = Transaction.builder()
                .id(130)
                .accountNumber("0000200002")
                .transactionType(1)
                .amount(3050.25)
                .transactionTime("")
                //.milliTransactionTime(1744119228453L)
                .build();

        given(transactionService.createTransaction(any(Transaction.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trx)));

        //then
        response.andDo(print())
            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber", is(trx.getAccountNumber())))
                .andExpect(jsonPath("$.transactionType", is(trx.getTransactionType())))
                .andExpect(jsonPath("$.amount", is(trx.getAmount())));
                //.andExpect(jsonPath("$.transactionTime", is(trx.getTransactionTime())));
                //.andExpect(jsonPath("$.milliTransactionTime", is(trx.getMilliTransactionTime())));

    }

    @Test
    @Order(2)
    @DisplayName("Get all transactions")
    void testGetTransaction() throws Exception {
        List<Transaction> lstTrx = new ArrayList<>();
        lstTrx.add(Transaction.builder().id(130).accountNumber("0000200000").transactionType(1).amount(3050.25).transactionTime("2025-04-07 13:12:15.504").milliTransactionTime(1744088619971L).build());
        lstTrx.add(Transaction.builder().id(131).accountNumber("0000200001").transactionType(1).amount(3055.25).transactionTime("2025-04-07 13:12:25.504").milliTransactionTime(1744088619972L).build());
        lstTrx.add(Transaction.builder().id(132).accountNumber("0000200002").transactionType(1).amount(3060.25).transactionTime("2025-04-07 13:12:35.504").milliTransactionTime(1744088619973L).build());
        lstTrx.add(Transaction.builder().id(133).accountNumber("0000200003").transactionType(1).amount(3065.25).transactionTime("2025-04-07 13:12:45.504").milliTransactionTime(1744088619974L).build());
        lstTrx.add(Transaction.builder().id(134).accountNumber("0000200004").transactionType(1).amount(3070.25).transactionTime("2025-04-07 13:12:55.504").milliTransactionTime(1744088619975L).build());
        given(transactionService.getTransactions()).willReturn(lstTrx);

        //when
        ResultActions response = mockMvc.perform(get("/transaction"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(lstTrx.size())));
    }

    @Test
    @Order(3)
    @DisplayName("Get transaction by Id")
    void testGetTransactionById() throws Exception {
        int id = 150;
        Transaction trx = Transaction.builder()
                .id(150)
                .accountNumber("0000200002")
                .transactionType(1)
                .amount(3050.25)
                .transactionTime("")
                //.milliTransactionTime(1744119228453L)
                .build();

        given(transactionService.getTransactionById(id)).willReturn(trx);

        //when
        ResultActions response = mockMvc.perform(get("/transaction/{transactionId}", id));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber", is(trx.getAccountNumber())))
                .andExpect(jsonPath("$.transactionType", is(trx.getTransactionType())))
                .andExpect(jsonPath("$.amount", is(trx.getAmount())));
        //.andExpect(jsonPath("$.transactionTime", is(trx.getTransactionTime())));
        //.andExpect(jsonPath("$.milliTransactionTime", is(trx.getMilliTransactionTime())));
    }

    @Test
    @Order(4)
    @DisplayName("Get transaction by account and apply rule")
    void testGetTransactionByAccountNumber() throws Exception {
        String accountNumber = "0000200002";
        List<Transaction> lstTrx = new ArrayList<>();
        lstTrx.add(Transaction.builder().id(130).accountNumber("0000200000").transactionType(1).amount(3050.25).transactionTime("2025-04-07 13:12:15.504").milliTransactionTime(1744088619971L).build());
        lstTrx.add(Transaction.builder().id(131).accountNumber("0000200001").transactionType(1).amount(3055.25).transactionTime("2025-04-07 13:12:25.504").milliTransactionTime(1744088619972L).build());
        lstTrx.add(Transaction.builder().id(132).accountNumber("0000200002").transactionType(1).amount(3060.25).transactionTime("2025-04-07 13:12:35.504").milliTransactionTime(1744088619973L).build());
        lstTrx.add(Transaction.builder().id(133).accountNumber("0000200003").transactionType(1).amount(3065.25).transactionTime("2025-04-07 13:12:45.504").milliTransactionTime(1744088619974L).build());
        lstTrx.add(Transaction.builder().id(134).accountNumber("0000200004").transactionType(1).amount(3070.25).transactionTime("2025-04-07 13:12:55.504").milliTransactionTime(1744088619975L).build());
        given(transactionService.getTransactionByAccountNumber(accountNumber)).willReturn(lstTrx);

        //when
        ResultActions response = mockMvc.perform(get("/transaction/account/{accountNumber}", accountNumber));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(lstTrx.size())));
    }

    @Disabled
    @Test
    @Order(5)
    @DisplayName("Delete transaction by Id")
    void testDeleteTransactionById() throws Exception {
        int id = 150;

        willDoNothing().given(transactionService).deleteTransactionById(id);

        //when
        ResultActions response = mockMvc.perform(delete("/transaction/{id}", id));

        response.andExpect(status().isOk())
                .andDo(print());
    }


}
