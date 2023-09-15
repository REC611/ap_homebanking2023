package com.ap.homebanking.controller;

import com.ap.homebanking.dto.DtoCard;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.CardColors;
import com.ap.homebanking.models.CardTypes;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repository.CardRepository;
import com.ap.homebanking.repository.ClientRepository;
import com.ap.homebanking.services.ServiceCard;
import com.ap.homebanking.services.ServiceClient;
import com.ap.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    ServiceClient serviceClient;
    @Autowired
    ServiceCard serviceCard;
    @PostMapping(value = "/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardTypes cardTypes, @RequestParam CardColors cardColors, Authentication authentication){
        Client client = serviceClient.findByEmail(authentication.getName());
        if (cardTypes == null || cardColors == null){
            return new ResponseEntity<>("\n" + "Select the type and color of your new card", HttpStatus.FORBIDDEN);
        }
        if ( client.getCards()
                .stream().filter(card -> card.getType().equals(cardTypes))
                .collect(Collectors.toList()).size() < 3 ){

            Card card = new Card(
                    (client.getFirstName() + " " + client.getLastName()), cardTypes, cardColors,
                    CardUtils.getCardNumber(),  CardUtils.getCardCvv(),
                    LocalDate.now(), LocalDate.now().plusYears(5));
            while (serviceCard.findByNumber(card.getNumber()) != null){
                card.setNumber( CardUtils.getCardNumber());
            }
            client.addCards(card);
            serviceCard.save(card);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Only 3 cards of each type can be generated", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "client/current/cards")
    public List<DtoCard> readCards (Authentication authentication){
        Client client= serviceClient.findByEmail(authentication.getName());
        return serviceCard.findByClientToCardDTO(client);
    }
    @RequestMapping(value = "/clients/current/cards/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCard(@PathVariable Long id){
        if (serviceCard.findById(id) != null){
            serviceCard.deleteCard(id);
            return new ResponseEntity<>(HttpStatus.PROCESSING);
        }else {
            return new ResponseEntity<>("The card to be deleted does not exist", HttpStatus.FORBIDDEN);
        }

    }
}
