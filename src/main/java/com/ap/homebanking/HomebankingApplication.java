package com.ap.homebanking;

import com.ap.homebanking.models.*;
import com.ap.homebanking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.ap.homebanking.models.TransactionType.CREDIT;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args -> {
			Client client01= new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client01);

			Account account01 = new Account("ABK001", LocalDate.now(), 100000);
			client01.addAccounts(account01);
			accountRepository.save(account01);

			Account account02 = new Account("ABK002", LocalDate.now().plusDays(1), 3500);
			client01.addAccounts(account02);
			accountRepository.save(account02);

			Client client02 = new Client("Julian F.", "Puebla Badano", "ejemplo@ejemplo");
			clientRepository.save(client02);

			Account account03 = new Account("ABK003", LocalDate.now(), 11000);
			client02.addAccounts(account03);
			accountRepository.save(account03);

			Account account04 = new Account("ABK004", LocalDate.now().plusDays(1), 7000);
			client02.addAccounts(account04);
			accountRepository.save(account04);

			Transaction transaction01 = new Transaction(TransactionType.CREDIT,9500, "Transfer made from own account", LocalDateTime.now());
			account01.addTransaction(transaction01);
			transactionRepository.save(transaction01);

			Transaction transaction02 = new Transaction(TransactionType.DEBIT,500, "Shopping Pay", LocalDateTime.now());
			account01.addTransaction(transaction02);
			transactionRepository.save(transaction02);

			Transaction transaction03 = new Transaction(TransactionType.CREDIT,1500, "Transfer made from American Bank", LocalDateTime.now());
			account01.addTransaction(transaction03);
			transactionRepository.save(transaction03);

			Loan loan01 = new Loan("Hipotecario", 500000.0, List.of(12,24,36,48,60));
			loanRepository.save(loan01);

			Loan loan02 = new Loan("Personal", 100000.0, List.of(6,12,24));
			loanRepository.save(loan02);

			Loan loan03 = new Loan("Automotriz", 300000.0, List.of(6,12,24,36));
			loanRepository.save(loan03);


			ClientLoan clientLoan01 = new ClientLoan( 100000.0, 60, client01, loan01);
			clientLoanRepository.save(clientLoan01);
			ClientLoan clientLoan02 = new ClientLoan(57500.0, 12, client01, loan02);
			clientLoanRepository.save(clientLoan02);

			ClientLoan clientLoan03 = new ClientLoan(300000.0, 48, client02, loan02);
			clientLoanRepository.save(clientLoan03);
			ClientLoan clientLoan04 = new ClientLoan(90000.0, 18, client02 ,loan03);
			clientLoanRepository.save(clientLoan04);

			Card card01 = new Card((client01.getFirstName() + " " + client01.getLastName()),
					CardTypes.DEBIT, CardColors.TITANIUM, "3496667569656449", 611,
					LocalDate.now(), LocalDate.now().plusDays(1730));
			client01.addCards(card01);
			cardRepository.save(card01);

			Card card02 = new Card((client01.getFirstName() + " " + client01.getLastName()),
					CardTypes.CREDIT, CardColors.TITANIUM, "3496582347883523", 311,
					LocalDate.now(), LocalDate.now().plusDays(1830));
			client01.addCards(card02);
			cardRepository.save(card02);

			Card card03 = new Card((client02.getFirstName() + " " + client02.getLastName()),
					CardTypes.CREDIT, CardColors.SILVER, "3496378867539992", 811,
					LocalDate.now(), LocalDate.now().plusDays(1825));
			client02.addCards(card03);
			cardRepository.save(card03);
		});
	}
}
