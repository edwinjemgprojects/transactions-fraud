package com.deutsche.transaction.services.impl;

import com.deutsche.transaction.entities.Transaction;
import com.deutsche.transaction.services.TransactionService;
import com.deutsche.transaction.repository.TransactionRepository;
import com.deutsche.transaction.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${validation.minimunAmount}")
    private double minimunAmount;

    @Value("${validation.olderThanHour}")
    private Long olderThanHour;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        boolean validate24Hours = validateAccountNumberAndGreater24Hours(transaction.getAccountNumber(), transaction);
        if(validate24Hours){
            return transactionRepository.save(transaction);
        }
        //System.out.println("emg590: Supero el monto permitido");
        return null;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        System.out.println("emg500: " + utils.getMilliMinimun());
        return transactionRepository.getTransactionById(transactionId);
    }

    @Override
    public List<Transaction> getTransactionByAccountNumber(String accountNumber) {
        return transactionRepository.getTransactionByAccountNumber(accountNumber);
    }

    @Override
    public Transaction deleteTransactionById(int id) {
        Transaction trx = getTransactionById(id);
        if (trx != null){
            Long secondsPassed = utils.getSecondsPassed(trx.getTransactionTime());
            //System.out.println("emg150: " + hoursPassed);
            if(secondsPassed <= (olderThanHour * 60 * 60)) { //86400 seconds = 24 hours, we need to convert into a seconds
                transactionRepository.deleteById(id);
                //System.out.println("emg150: " + secondsPassed);
            }
            return trx;

            //System.out.println("emg160: " + secondsPassed);
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactionByAccountNumberAndGreaterDate(String accountNumber) {
        //String transactionTime = "";
        Long milliTransactionTime = utils.getMilliMinimun();
        //System.out.println("emg150: " + milliTransactionTime);
        return transactionRepository.getTransactionByAccountNumberAndGreaterDate(accountNumber, milliTransactionTime);
        //return validateAccountNumberAndGreater24Hours(accountNumber);
        //return null;
    }

    public boolean validateAccountNumberAndGreater24Hours(String accountNumber, Transaction transaction) {
        Long milliTransactionTime = utils.getMilliMinimun();
        //System.out.println("emg150: " + milliTransactionTime);
        List<Transaction> lstTransactions = transactionRepository.getTransactionByAccountNumberAndGreaterDate(accountNumber, milliTransactionTime);
        double sumAmount = transaction.getAmount(); //actual amount requested

        for(Transaction trx: lstTransactions){
            sumAmount += trx.getAmount();
        }
        System.out.println("emg180: " + sumAmount);
        //System.out.println("emg200: " + minimunAmount);

        if(sumAmount > minimunAmount){
            return false;
        }

        //return lstTransactions;
        return true;
    }


}
