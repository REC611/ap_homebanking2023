package com.ap.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();
    private String firstName;
    private String lastName;
    private String email;

    public Set<Account> getAccounts(){
        return accounts;
    }
    public void addAccounts(Account account){
        account.setClient(this);
        accounts.add(account);
    }
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<ClientLoan> loans = new HashSet<>();
    public Set<ClientLoan> getLoans(){
        return loans;
    }

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    public Set<Card> getCards(){ return cards;}
    public void addCards(Card card){
        card.setClient(this);
        cards.add(card);
    }

    public Client() {}

    public Client(String firstName, String lastName, String email){

        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
    }

    //Utilizo getters and setters
    public Long getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
