package com.deutsche.transaction.repository;

import com.deutsche.transaction.entities.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
    Transaction getTransactionById(int id);
    List<Transaction> getTransactionByAccountNumber(String accountNumber);

    //@Aggregation("{$group: {accountNumber: ?0, total:{$sum: $amount}}}")

    @Query("{accountNumber: ?0, milliTransactionTime: {$gte: ?1 }}")
    List<Transaction> getTransactionByAccountNumberAndGreaterDate(String accountNumber, Long milliTransactionTime);
}
