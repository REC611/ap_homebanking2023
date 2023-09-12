package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repository.TransactionRepository;
import com.ap.homebanking.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTransactionImplement implements ServiceTransaction {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
