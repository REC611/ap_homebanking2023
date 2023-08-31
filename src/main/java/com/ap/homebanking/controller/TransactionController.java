package com.ap.homebanking.controller;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.repository.AccountRepository;
import com.ap.homebanking.repository.ClientRepository;
import com.ap.homebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Transactional
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransactions(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam Double amount, @RequestParam String description, Authentication authentication) {

        if (fromAccountNumber.isEmpty() || toAccountNumber.isEmpty() || amount.isNaN() || description.isEmpty()) {
            return new ResponseEntity<>("Missing data to make the transaction", HttpStatus.FORBIDDEN);
        }
        Account fromAccount = accountRepository.findByNumber(fromAccountNumber);
        if (fromAccount == null) {
            return new ResponseEntity<>("The source account number does not exist", HttpStatus.FORBIDDEN);
        }
        Account toAccount = accountRepository.findByNumber(toAccountNumber);
        if (toAccount == null) {
            return new ResponseEntity<>("The destination account number does not exist", HttpStatus.FORBIDDEN);
        }
        if (fromAccount.equals(toAccount)){
            return new ResponseEntity<>("The origin and destination account number is the same", HttpStatus.FORBIDDEN);
        }
        Client client = clientRepository.findByEmail(authentication.getName());
        if (!client.getAccounts().contains(fromAccount)){
            return new ResponseEntity<>("The source account number does not correspond to the client",HttpStatus.FORBIDDEN);
        }
        if (fromAccount.getBalance()< amount){
            return new ResponseEntity<>("You do not have sufficient funds to carry out the transaction",HttpStatus.FORBIDDEN);
        }

        Transaction debit = new Transaction(TransactionType.DEBIT, amount * -1, description + " " + fromAccount.getNumber() ,
                LocalDateTime.now());
        fromAccount.addTransaction(debit);
        transactionRepository.save(debit);
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        Transaction credit = new Transaction(TransactionType.CREDIT, amount, description + " " + toAccount.getNumber(),
                LocalDateTime.now());
        toAccount.addTransaction(credit);
        transactionRepository.save(credit);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);

        return new ResponseEntity<>("Transaction was sent successfully", HttpStatus.CREATED);
    }
}
