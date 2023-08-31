package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoCard;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.CardColors;
import com.ap.homebanking.models.CardTypes;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.CardRepository;
import com.ap.homebanking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CardRepository cardRepository;
    @RequestMapping(value = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> createCard(@RequestParam CardTypes cardTypes, @RequestParam CardColors cardColors, Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        if (cardTypes == null || cardColors == null){
            return new ResponseEntity<>("Seleccione el tipo y color de su nueva tarjeta", HttpStatus.FORBIDDEN);
        }
        if ( client.getCards()
                .stream().filter(card -> card.getType().equals(cardTypes))
                .collect(Collectors.toList()).size() < 3 ){

            Card card = new Card(
                    (client.getFirstName() + " " + client.getLastName()), cardTypes, cardColors,
                    ((int)(Math.random() * 9999 + 1)) + "-" + ((int)(Math.random() * 9999 + 1)) + "-" +
                            ((int)(Math.random() * 9999 + 1)) + "-" + ((int)(Math.random() * 9999 + 1)),
                    (int)(Math.random() * 999 + 1), LocalDate.now(), LocalDate.now().plusYears(5));
            while (cardRepository.findByNumber(card.getNumber()) != null){
                card.setNumber(
                        ((int)(Math.random() * 9999 + 1)) + "-" + ((int)(Math.random() * 9999 + 1)) + "-" +
                                ((int)(Math.random() * 9999 + 1)) + "-" + ((int)(Math.random() * 9999 + 1))
                );
            }
            client.addCards(card);
            cardRepository.save(card);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Solamente se pueden generar 3 tarjetas de cada tipo", HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "client/current/cards")
    public List<DtoCard> readCards (Authentication authentication){
        Client client= clientRepository.findByEmail(authentication.getName());
        return cardRepository.findByClient(client).stream().map(card -> new DtoCard(card)).collect(Collectors.toList());
    }
}
