package com.ap.homebanking.repositories;


import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
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
        assertThat(clients, hasItem(hasProperty("email", containsString("@"))));
    }
}
