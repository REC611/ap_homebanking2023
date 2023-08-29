package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoAccount;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.AccountRepository;
import com.ap.homebanking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @RequestMapping(value = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication){

        Client client = clientRepository.findByEmail(authentication.getName());

        if (client.getAccounts().size() < 3 ){
            Account account = new Account("ABK-" + ((int)(Math.random() * 99999999 + 1)), LocalDate.now(), 0);
            client.addAccounts(account);
            accountRepository.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Solamente se pueden generar 3 cuentas por cliente", HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/clients/current/accounts")
    public List<DtoAccount> readAccounts(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return accountRepository.findByClient(client)
                .stream().map(account -> new DtoAccount(account))
                .collect(Collectors.toList());
    }
    @RequestMapping("/account")
    public List<DtoAccount> getAccounts(){
        return accountRepository.findAll().stream().map(account -> new DtoAccount(account)).collect(Collectors.toList());
    }
    @RequestMapping("/accounts/{id}")
    public DtoAccount getAccount(@PathVariable Long id){
        return accountRepository.findById(id).map(account -> new DtoAccount(account)).orElse(null);
    }
}
