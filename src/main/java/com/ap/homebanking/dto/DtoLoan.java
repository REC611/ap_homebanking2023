package com.ap.homebanking.dto;

import com.ap.homebanking.models.Loan;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class DtoLoan {
    private Long id;
    private String name;
    private Double maxAmount;
    @ElementCollection
    private List<Integer> payments;

    public DtoLoan() {
    }
    public DtoLoan(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    /** GETTERS */
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getMaxAmount() {
        return maxAmount;
    }
    public List<Integer> getPayments() {
        return payments;
    }
}
