package com.ap.homebanking;

import com.ap.homebanking.utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CardUtilsTest {
    @Test
    void cardNumberIsCreated(){
        String cardNumber = CardUtils.getCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }

    @Test
    void cardNumberMiddleDash(){
        String cardNumber = CardUtils.getCardNumber();
        assertThat(cardNumber, containsString("-"));
    }

    @Test
    void cardCvvIsCreated(){
        int cardCvv = CardUtils.getCardCvv();
        assertThat(cardCvv, is(notNullValue()));
    }

    @Test
    void cardCvvGreaterThanZero(){
        int cardCvv = CardUtils.getCardCvv();
        assertThat(cardCvv, greaterThan(0));
    }
}
