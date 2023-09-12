package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.ClientLoan;
import com.ap.homebanking.repository.ClientLoanRepository;
import com.ap.homebanking.services.ServiceClientLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientLoanImplement implements ServiceClientLoan {
    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @Override
    public void save(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
