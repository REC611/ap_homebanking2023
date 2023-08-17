package com.ap.homebanking.dto;

import com.ap.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoClient {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<DtoAccount> accounts;
    private Set<DtoLoanClient> loans;
    private Set<DtoCard> cards;
    public DtoClient(){
    }

    public DtoClient(Client client){
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts()
                .stream().map(account -> new DtoAccount(account)).collect(Collectors.toSet());
        this.loans = client.getLoans().stream().map(clientLoan -> new DtoLoanClient(clientLoan)).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(card -> new DtoCard(card)).collect(Collectors.toSet());
    }

    public Long getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }

    public Set<DtoAccount> getAccounts(){
        return accounts;
    }

    public Set<DtoLoanClient> getLoans() {
        return loans;
    }
    public Set<DtoCard> getCards(){
        return cards;
    }
}
