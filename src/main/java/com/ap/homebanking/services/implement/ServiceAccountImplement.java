package com.ap.homebanking.services.implement;

import com.ap.homebanking.dto.DtoAccount;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.AccountRepository;
import com.ap.homebanking.services.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class ServiceAccountImplement implements ServiceAccount {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }
    @Override
    public DtoAccount findByIdToAccountDTO(Long id) {
        return accountRepository.findById(id)
                .map(account -> new DtoAccount(account))
                .orElse(null);
    }
    @Override
    public List<DtoAccount> findByClientToListAccountDTO(Client client) {
        return accountRepository.findByClient(client).stream()
                .map(account -> new DtoAccount(account))
                .collect(Collectors.toList());
    }
    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
