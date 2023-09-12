package com.ap.homebanking.services;

import com.ap.homebanking.dto.DtoCard;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;

import java.util.List;

public interface ServiceCard {
    void save(Card card);
    Card findByNumber(String number);
    List<DtoCard> findByClientToCardDTO(Client client);
}
