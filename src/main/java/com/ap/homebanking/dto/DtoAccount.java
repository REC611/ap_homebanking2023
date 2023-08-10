package com.ap.homebanking.dto;

import com.ap.homebanking.models.Account;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoAccount {
    private Long id;
    private String number;
    private double balance;
    private LocalDate creationDate;
    private Set<DtoTransaction> transactions;

    public DtoAccount(){
    }

    public DtoAccount(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(transaction -> new DtoTransaction(transaction)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }
    public String getNumber(){
        return number;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public double getBalance() {
        return balance;
    }
    public Set<DtoTransaction> getTransactions(){
        return transactions;
    }
}
