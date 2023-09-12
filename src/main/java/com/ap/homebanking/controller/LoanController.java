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
            return new ResponseEntity<>("Los datos ingresados no son válidos", HttpStatus.FORBIDDEN);
        }
        if (dtoLoanApplication.getAmount() == 0){
            return new ResponseEntity<>("El monto solicitado no puede ser 0", HttpStatus.FORBIDDEN);
        }
        if (dtoLoanApplication.getPayments() == 0){
            return new ResponseEntity<>("Las cuotas seleccionadas no pueden ser 0", HttpStatus.FORBIDDEN);
        }

        Loan loan = serviceLoan.findById(dtoLoanApplication.getLoanId());

        if (loan == null){
            return new ResponseEntity<>("El prestamo solicitado no existe", HttpStatus.FORBIDDEN);
        }

        if (dtoLoanApplication.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("El monto solicitado supera el máximo permitido para el tipo de préstamo", HttpStatus.FORBIDDEN);
        }

        if (!loan.getPayments().contains(dtoLoanApplication.getPayments())){
            return new ResponseEntity<>("La cantidad de cuotas solicitada no está disponible par el tipo de préstamo", HttpStatus.FORBIDDEN);
        }

        Account toAccount = serviceAccount.findByNumber(dtoLoanApplication.getToAccountNumber());

        if (toAccount == null){
            return new ResponseEntity<>("La cuenta de destino no existe", HttpStatus.FORBIDDEN);
        }

        Client client = serviceClient.findByEmail(authentication.getName());

        if (!client.getAccounts().contains(toAccount)){
            return new ResponseEntity<>("La cuenta destino no pertenece al cliente", HttpStatus.FORBIDDEN);
        }

        Double loanAmount = dtoLoanApplication.getAmount() + (dtoLoanApplication.getAmount() * 0.2);

        ClientLoan clientLoan = new ClientLoan(loanAmount, dtoLoanApplication.getPayments());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);
        serviceClientLoan.save(clientLoan);

        Transaction credit = new Transaction(TransactionType.CREDIT, dtoLoanApplication.getAmount(),
                "New loan " + loan.getName() + " to account " + toAccount.getNumber() + ". Loan approved", LocalDateTime.now());
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
