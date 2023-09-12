package com.ap.homebanking.services;

import com.ap.homebanking.dto.DtoClient;
import com.ap.homebanking.models.Client;

import java.util.List;

public interface ServiceClient {
    void save(Client client);
    List<DtoClient> findAllToListClientDTO();
    Client findByEmail(String email);
    DtoClient findByEmailToClientDTO(String email);
}
