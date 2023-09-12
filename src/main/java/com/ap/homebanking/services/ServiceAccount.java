package com.ap.homebanking.services;

import com.ap.homebanking.dto.DtoAccount;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;

import java.util.List;

public interface ServiceAccount {
     void save(Account account);
     Account findByNumber(String number);
     DtoAccount findByIdToAccountDTO(Long id);
     List<DtoAccount> findByClientToListAccountDTO(Client client);
}
