package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repository.TransactionRepository;
import com.ap.homebanking.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceTransactionImplement implements ServiceTransaction {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
    @Override
    public List<Transaction> findAllByAccount(Account account) {
        return transactionRepository.findByAccount(account);
    }
    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
