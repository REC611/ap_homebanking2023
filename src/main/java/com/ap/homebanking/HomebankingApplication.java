package com.ap.homebanking;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.AccountRepository;
import com.ap.homebanking.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){
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

			Account account03 = new Account("ABK001", LocalDate.now(), 11000);
			client02.addAccounts(account03);
			accountRepository.save(account03);

			Account account04 = new Account("ABK002", LocalDate.now().plusDays(1), 7000);
			client02.addAccounts(account04);
			accountRepository.save(account04);
		});
	}
}
