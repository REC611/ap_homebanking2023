package com.ap.homebanking;

import com.ap.homebanking.models.*;
import com.ap.homebanking.utils.CardUtils;
import com.ap.homebanking.repository.*;
import com.ap.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {
/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ServiceClient serviceClient;
	@Autowired
	private ServiceTransaction serviceTransaction;
	@Autowired
	private ServiceAccount serviceAccount;
	@Autowired
	private ServiceLoan serviceLoan;
	@Autowired
	private ServiceCard serviceCard;
	@Autowired
	private ServiceClientLoan serviceClientLoan;

*/

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(){
		return (args -> {
/*
			Client client01= new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba"));
			serviceClient.save(client01);

			Account account01 = new Account("ABK-" + ((int)(Math.random() * 99999999 + 1)),
					LocalDate.now(), 100000.0);
			client01.addAccounts(account01);
			serviceAccount.save(account01);

			Account account02 = new Account("ABK-" + ((int)(Math.random() * 99999999 + 1)),
					LocalDate.now().plusDays(1), 3500.0);
			client01.addAccounts(account02);
			serviceAccount.save(account02);

			Client client02 = new Client("Julian F.", "Puebla Badano", "ejemplo@ejemplo", passwordEncoder.encode("pass"));
			serviceClient.save(client02);

			Client client03 = new Client("Administrador", "HomeBanking", "admin@admin", passwordEncoder.encode("admin"));
			serviceClient.save(client03);

			Transaction transaction01 = new Transaction(TransactionType.CREDIT,9500, "Transfer made from own account", LocalDateTime.now(), account01.getBalance());
			account01.addTransaction(transaction01);
			serviceTransaction.save(transaction01);

			Transaction transaction02 = new Transaction(TransactionType.DEBIT,500, "Shopping Pay", LocalDateTime.now(), account01.getBalance());;
			account01.addTransaction(transaction02);
			serviceTransaction.save(transaction02);

			Transaction transaction03 = new Transaction(TransactionType.CREDIT,1500, "Transfer made from American Bank", LocalDateTime.now(), account01.getBalance());;
			account01.addTransaction(transaction03);
			serviceTransaction.save(transaction03);

			Loan loan01 = new Loan("Hipotecario", 500000.0, List.of(12,24,36,48,60));
			serviceLoan.save(loan01);

			Loan loan02 = new Loan("Personal", 100000.0, List.of(6,12,24));
			serviceLoan.save(loan02);

			Loan loan03 = new Loan("Automotriz", 300000.0, List.of(6,12,24,36));
			serviceLoan.save(loan03);


			ClientLoan clientLoan01 = new ClientLoan( 100000.0, 60);
			client01.addClientLoan(clientLoan01);
			loan01.addClientLoan(clientLoan01);
			serviceClientLoan.save(clientLoan01);

			ClientLoan clientLoan02 = new ClientLoan(57500.0, 12);
			client02.addClientLoan(clientLoan02);
			loan01.addClientLoan(clientLoan02);
			serviceClientLoan.save(clientLoan02);

			ClientLoan clientLoan03 = new ClientLoan(300000.0, 48);
			client01.addClientLoan(clientLoan03);
			loan02.addClientLoan(clientLoan03);
			serviceClientLoan.save(clientLoan03);

			ClientLoan clientLoan04 = new ClientLoan(90000.0, 18);
			client02.addClientLoan(clientLoan04);
			loan03.addClientLoan(clientLoan04);
			serviceClientLoan.save(clientLoan04);

			Card card01 = new Card((client01.getFirstName() + " " + client01.getLastName()),
					CardTypes.DEBIT, CardColors.TITANIUM,CardUtils.getCardNumber(), CardUtils.getCardCvv(), LocalDate.now(), LocalDate.now().plusYears(-5));
			client01.addCards(card01);
			serviceCard.save(card01);

			Card card02 = new Card((client01.getFirstName() + " " + client01.getLastName()),
					CardTypes.CREDIT, CardColors.TITANIUM, CardUtils.getCardNumber(), CardUtils.getCardCvv(), LocalDate.now(), LocalDate.now().plusYears(5));
			client01.addCards(card02);
			serviceCard.save(card02);

			Card card03 = new Card((client02.getFirstName() + " " + client02.getLastName()),
					CardTypes.CREDIT, CardColors.SILVER, CardUtils.getCardNumber(), CardUtils.getCardCvv(), LocalDate.now(), LocalDate.now().plusYears(5));
			client02.addCards(card03);
			serviceCard.save(card03);

*/
		});
		/* --- */
	}
}
