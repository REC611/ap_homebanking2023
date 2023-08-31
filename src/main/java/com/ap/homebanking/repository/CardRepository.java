package com.ap.homebanking.repository;

import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
    public List<Card> findByClient(Client client);
    public Card findByNumber(String number);
}
