package com.ap.homebanking.services;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Transaction;

import java.util.List;

public interface ServiceTransaction {
    void save(Transaction transaction);
    List<Transaction> findAllByAccount(Account account);
    void deleteById(Long id);
}
