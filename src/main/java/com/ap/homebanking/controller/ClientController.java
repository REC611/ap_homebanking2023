package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoClient;
import com.ap.homebanking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @RequestMapping("/clients")
    public List<DtoClient> getClients(){
        return clientRepository.findAll()
                .stream().map(client -> new DtoClient(client))
                .collect(Collectors.toList());
    }
    @RequestMapping("/clients/{id}")
    public DtoClient getclient(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(client -> new DtoClient(client))
                .orElse(null);
    }
}
