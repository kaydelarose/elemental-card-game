package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    private Hand hand;
    private Card card1;
    private Card card2;

    @BeforeEach
    public void setUp() {

        hand = new Hand();
        card1 = new Card("FireBall", "Fire", 50, "damage");
        card2 = new Card("Healing Water", "Water", 30, "healing");

    }

    @Test
    public void newCard_shouldAdd_toHand() {
        hand.addCard(card1);
        ArrayList<Card> cards = hand.getCards();
        assertTrue(cards.contains(card1));
    }

    @Test
    public void removeCard_shouldRemove_fromHand() {
        hand.addCard(card1);
        hand.removeCard(card1);
        ArrayList<Card> cards = hand.getCards();
        assertFalse(cards.contains(card1));
    }

    @Test
    public void getCards_shouldReturn_correctCardSize() {
        hand.addCard(card1);
        hand.addCard(card2);
        ArrayList<Card> cards = hand.getCards();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

}
