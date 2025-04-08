package com.deutsche.transaction.services;

import com.deutsche.transaction.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    Transaction getTransactionById(int transactionId);
    List<Transaction> getTransactionByAccountNumber(String accountNumber);
    Transaction deleteTransactionById(int id);
    List<Transaction> getTransactionByAccountNumberAndGreaterDate(String accountNumber);
}
