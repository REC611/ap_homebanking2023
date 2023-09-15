package com.ap.homebanking.repositories;

import com.ap.homebanking.models.Card;
import com.ap.homebanking.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class CardRepositoryTest {
    @Autowired
    CardRepository cardRepository;

    @Test
    public void exisCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }
    @Test
    public void existTypeCard() {
        List<Card> cards = cardRepository.findAll();
        for (Card card :cards) {
            assertEquals(3, (String.valueOf(card.getCvv())).length());
        }


    }
}
