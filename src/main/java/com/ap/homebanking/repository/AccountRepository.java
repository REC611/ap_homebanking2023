package com.ap.homebanking.repository;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findByClient(Client client);
    public Account findByNumber(String number);
}
