package com.niantic.models;

public class Card {
    private String cardName;
    private String cardElement;
    private int cardValue;
    private String effectType; // "damage", "healing", "paralyzed"

    public Card(String cardName, String cardElement, int cardValue, String effectType) {
        this.cardName = cardName;
        this.cardElement = cardElement;
        this.cardValue = cardValue;
        this.effectType = effectType;
    }

    public Card() {

    }

    public String getCardName() {
        return cardName;
    }

    public String getCardElement() {
        return cardElement;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String getEffectType() {
        return effectType;
    }

    // for testing and debugging
    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", element='" + cardElement + '\'' +
                ", value=" + cardValue +
                ", effectType='" + effectType + '\'' +
                '}';
    }
}
