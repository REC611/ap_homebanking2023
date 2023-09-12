package com.ap.homebanking.services.implement;

import com.ap.homebanking.dto.DtoLoan;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.repository.LoanRepository;
import com.ap.homebanking.services.ServiceLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLoanImplement implements ServiceLoan {

    @Autowired
    private LoanRepository loanRepository;
    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }
    @Override
    public List<DtoLoan> findAllToListLoanDTO() {
        return loanRepository.findAll().stream()
                .map(loan -> new DtoLoan(loan))
                .collect(Collectors.toList());
    }
    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

}
