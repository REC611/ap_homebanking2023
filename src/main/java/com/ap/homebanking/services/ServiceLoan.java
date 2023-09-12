package com.ap.homebanking.services;

import com.ap.homebanking.dto.DtoLoan;
import com.ap.homebanking.models.Loan;

import java.util.List;

public interface ServiceLoan {
    void save (Loan loan);
    List<DtoLoan> findAllToListLoanDTO();
    Loan findById(Long id);
}
