package com.ap.homebanking.repositories;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void existAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,is(not(empty())));
    }
    @Test
    public void  AccountBalancePositive(){
        List<Account> accounts = accountRepository.findAll();
        for (Account account: accounts) {
            assertThat(account.getNumber(), isA(String.class));
        }
    }
}
