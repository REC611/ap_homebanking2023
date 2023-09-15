package com.ap.homebanking.services.implement;

import com.ap.homebanking.dto.DtoCard;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.CardRepository;
import com.ap.homebanking.services.ServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCardImplement implements ServiceCard {
    @Autowired
    private CardRepository cardRepository;
    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }
    @Override
    public List<DtoCard> findByClientToCardDTO(Client client) {
        return cardRepository.findByClient(client)
                .stream().map(card -> new DtoCard(card))
                .collect(Collectors.toList());
    }
    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
