package com.deutsche.transaction.entities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.deutsche.transaction.utils.utils;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("transactions")
public class Transaction {
    @Id
    private int id;
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        LocalDateTime ldt = LocalDateTime.now();
        this.transactionTime = utils.getFormatDate(ldt);
        //setMilliTransactionTime(utils.getMilliSeconds(ldt));
        this.milliTransactionTime = utils.getMilliSeconds(ldt);
        //this.transactionTime = transactionTime;
        //this.transactionTime = LocalDateTime.now().toString();
    }

    private String accountNumber;
    private int transactionType;    //0:Debit; 1:Credit
    private double amount;
    private String transactionTime;

    private Long milliTransactionTime;
    public Long getMilliTransactionTime() {
        return milliTransactionTime;
    }
/*
    public void setMilliTransactionTime(Long milliTransactionTime) {
        this.milliTransactionTime = milliTransactionTime;
    }
*/

}
