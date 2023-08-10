package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoAccount;
import com.ap.homebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/account")
    public List<DtoAccount> getAccounts(){
        return accountRepository.findAll().stream().map(account -> new DtoAccount(account)).collect(Collectors.toList());
    }
    @RequestMapping("/accounts/{id}")
    public DtoAccount getAccount(@PathVariable Long id){
        return accountRepository.findById(id).map(account -> new DtoAccount(account)).orElse(null);
    }
}
