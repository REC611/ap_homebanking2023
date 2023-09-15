package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoAccount;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.services.ServiceAccount;
import com.ap.homebanking.services.ServiceClient;
import com.ap.homebanking.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private ServiceAccount serviceAccount;
    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceTransaction serviceTransaction;
    @PostMapping(value = "/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication){

        Client client = serviceClient.findByEmail(authentication.getName());

        if (client.getAccounts().size() < 3 ){
            Account account = new Account("ABK-" + ((int)(Math.random() * 99999999 + 1)), LocalDate.now(), 0.0);

            while ( serviceAccount.findByNumber(account.getNumber()) != null){
                account.setNumber("ABK-" + ((int)(Math.random()*99999999+1)));
            }
            client.addAccounts(account);
            serviceAccount.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Only 3 accounts can be generated per client", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/clients/current/accounts")
    public List<DtoAccount> readAccounts(Authentication authentication){
        Client client = serviceClient.findByEmail(authentication.getName());
        return serviceAccount.findByClientToListAccountDTO(client);
    }
    /*
    @RequestMapping("/account")
    public List<DtoAccount> getAccounts(){
        return serviceAccount.findAll().stream().map(account -> new DtoAccount(account)).collect(Collectors.toList());
    }
    */
    @GetMapping("/accounts/{id}")
    public DtoAccount readAccount(@PathVariable Long id){
        return serviceAccount.findByIdToAccountDTO(id);
    }
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id){
        Account account = serviceAccount.findById(id);
        if ( account != null){
            List<Transaction> transactions = serviceTransaction.findAllByAccount(account);
            if (transactions != null){
                for (Transaction transaction : transactions ) {
                    serviceTransaction.deleteById(transaction.getId());
                }
            }
            serviceAccount.deleteById(account.getId());
            return new ResponseEntity<>(HttpStatus.PROCESSING);
        } else {
            return new ResponseEntity<>("The account you want to delete does not exist", HttpStatus.FORBIDDEN);
        }
    }
}
