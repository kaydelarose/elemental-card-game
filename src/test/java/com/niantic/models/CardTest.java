package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest
{
    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card("FireBall","Fire", 50, "damage");
    }

    @Test
    public void getCardName_shouldReturn_cardName() {
        assertEquals("FireBall", card.getCardName());
    }

    @Test
    public void getElement_shouldReturn_elementType() {
        assertEquals("Fire", card.getCardElement());
    }

    @Test
    public void getValue_shouldReturn_cardValue() {
        assertEquals(50, card.getCardValue());
    }

    @Test
    public void getEffectType_shouldReturn_typeOfEffect() {
        assertEquals("damage", card.getEffectType());
    }



}