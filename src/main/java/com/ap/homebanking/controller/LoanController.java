package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoLoan;
import com.ap.homebanking.dto.DtoLoanApplication;
import com.ap.homebanking.models.*;
import com.ap.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private ServiceLoan serviceLoan;
    @Autowired
    private ServiceAccount serviceAccount;
    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceClientLoan serviceClientLoan;
    @Autowired
    private ServiceTransaction serviceTransaction;

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@RequestBody DtoLoanApplication dtoLoanApplication,
                                             Authentication authentication){

        if (dtoLoanApplication.getLoanId() == null || dtoLoanApplication.getAmount() == null ||
                dtoLoanApplication.getPayments() == null || dtoLoanApplication.getToAccountNumber() == null){
            return new ResponseEntity<>("The data entered is not valid", HttpStatus.FORBIDDEN);
        }
        if (dtoLoanApplication.getAmount() == 0){
            return new ResponseEntity<>("The requested amount cannot be 0", HttpStatus.FORBIDDEN);
        }
        if (dtoLoanApplication.getPayments() == 0){
            return new ResponseEntity<>("The selected quotas cannot be 0", HttpStatus.FORBIDDEN);
        }

        Loan loan = serviceLoan.findById(dtoLoanApplication.getLoanId());

        if (loan == null){
            return new ResponseEntity<>("The requested loan does not exist", HttpStatus.FORBIDDEN);
        }

        if (dtoLoanApplication.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("The requested amount exceeds the maximum allowed for the type of loan", HttpStatus.FORBIDDEN);
        }

        if (!loan.getPayments().contains(dtoLoanApplication.getPayments())){
            return new ResponseEntity<>("The requested amount of installments is not available for the type of loan", HttpStatus.FORBIDDEN);
        }

        Account toAccount = serviceAccount.findByNumber(dtoLoanApplication.getToAccountNumber());

        if (toAccount == null){
            return new ResponseEntity<>("The destination account does not exist", HttpStatus.FORBIDDEN);
        }

        Client client = serviceClient.findByEmail(authentication.getName());

        if (!client.getAccounts().contains(toAccount)){
            return new ResponseEntity<>("The destination account does not belong to the client", HttpStatus.FORBIDDEN);
        }

        Double loanAmount = dtoLoanApplication.getAmount() + (dtoLoanApplication.getAmount() * 0.2);

        ClientLoan clientLoan = new ClientLoan(loanAmount, dtoLoanApplication.getPayments());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);
        serviceClientLoan.save(clientLoan);

        Transaction credit = new Transaction(TransactionType.CREDIT, dtoLoanApplication.getAmount(),
                "New loan " + loan.getName() + " to account " + toAccount.getNumber() + ". Loan approved",
                LocalDateTime.now(), toAccount.getBalance() + dtoLoanApplication.getAmount() );
        toAccount.addTransaction(credit);
        serviceTransaction.save(credit);
        toAccount.setBalance(toAccount.getBalance() + dtoLoanApplication.getAmount());
        serviceAccount.save(toAccount);

        return new ResponseEntity<>("OK",HttpStatus.CREATED);

    }
    @GetMapping("/loans")
    public List<DtoLoan> readLoans(){
        return serviceLoan.findAllToListLoanDTO();
    }
}
