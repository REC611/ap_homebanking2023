package com.ap.homebanking.services.implement;

import com.ap.homebanking.dto.DtoClient;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.ClientRepository;
import com.ap.homebanking.services.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceClientImplement implements ServiceClient {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }
    @Override
    public List<DtoClient> findAllToListClientDTO() {
        return clientRepository.findAll().stream()
                .map(client -> new DtoClient(client))
                .collect(Collectors.toList());
    }
    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    @Override
    public DtoClient findByEmailToClientDTO(String email) {
        return new DtoClient(clientRepository.findByEmail(email));
    }
}
