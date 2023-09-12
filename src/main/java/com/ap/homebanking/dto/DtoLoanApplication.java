package com.ap.homebanking.dto;

public class DtoLoanApplication {
    private Long loanId;
    private Double amount;
    private Integer payments;
    private String toAccountNumber;

    public Long getLoanId() {
        return loanId;
    }
    public Double getAmount() {
        return amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
