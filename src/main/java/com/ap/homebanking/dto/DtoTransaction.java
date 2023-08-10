package com.ap.homebanking.dto;


import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class DtoTransaction {
    private Long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;
    public DtoTransaction(){
    }

    public DtoTransaction(Transaction transaction){
        this.id = transaction.getId();
        this.type=transaction.getType();
        this.amount=transaction.getAmount();
        this.date=transaction.getDate();
        this.description = transaction.getDescription();
    }
    public Long getId() {
        return id;
    }
    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
}
