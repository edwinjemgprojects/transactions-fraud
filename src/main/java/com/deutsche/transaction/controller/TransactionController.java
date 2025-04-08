package com.deutsche.transaction.controller;

import com.deutsche.transaction.entities.Transaction;
import com.deutsche.transaction.services.TransactionService;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpServerErrorException;
//import com.deutsche.transaction.exceptions.InternalServerException;
//import com.deutsche.transaction.entities.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    //@ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction){
        try {
            Transaction trxNew = transactionService.createTransaction(transaction);
            //System.out.println("emg10");
            //System.out.println(trxNew);
            HttpHeaders headers = new HttpHeaders();
            if (trxNew != null) {
                headers.add("Status", "Transaction saved.");
                return new ResponseEntity<>(trxNew, headers, HttpStatus.CREATED);
            } else {
                headers.add("Status", "Account exceeds a predefined threshold.");
                return new ResponseEntity<>(null, headers, HttpStatus.NOT_IMPLEMENTED);
            }
            //return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transaction));
            //return null;
        }catch(Exception e){
            //throw new InternalServerErrorException("An internal server error ocurred.");
            //ErrorResponse httpError = new ErrorResponse(LocalDateTime.now(), e.getMessage() , "Internal Server Error"));
                    /*
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(LocalDateTime.now(), "Internal Server Error"));
*/
            HttpHeaders headers = new HttpHeaders();
            headers.add("Status", "Internal Server Error, contact TI.");
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(){
        //return ResponseEntity.ok(transactionService.getTransactions());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "OK");
        return new ResponseEntity<>(transactionService.getTransactions(), headers, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int transactionId){
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountNumber(@PathVariable String accountNumber){
        return ResponseEntity.ok(transactionService.getTransactionByAccountNumber(accountNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransactionById(@PathVariable int id){
        Transaction trxDeleted = transactionService.deleteTransactionById(id);
        HttpHeaders headers = new HttpHeaders();
        if(trxDeleted != null){
            headers.add("Status", "Transaction deleted.");
            return new ResponseEntity<>(trxDeleted, headers, HttpStatus.ACCEPTED);
        }
        headers.add("Status", "Transaction not found.");
        return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        //return ResponseEntity.ok(transactionService.deleteTransactionById(id));
    }

    @GetMapping("/accountGreaterDate/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountNumberAndGreaterDate(@PathVariable String accountNumber){
        return ResponseEntity.ok(transactionService.getTransactionByAccountNumberAndGreaterDate(accountNumber));
    }



}
