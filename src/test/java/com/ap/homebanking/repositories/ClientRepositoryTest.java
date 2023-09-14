package com.ap.homebanking.repositories;


import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.RoleType;
import com.ap.homebanking.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }

    @Test
    public void existPersonalAccount() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            if(client.getRole().compareTo(RoleType.ADMIN)!=0){
                assertThat(client.getAccounts().size(), greaterThan(0));
            }

        }
    }
}
