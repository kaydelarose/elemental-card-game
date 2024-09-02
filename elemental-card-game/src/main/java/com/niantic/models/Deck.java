package com.niantic.models;

import java.util.*;

public class Deck {
    private ArrayList<Card> cards;
    private Map<String, String> cardEffectMap;
    private LinkedHashMap<String, String[]> elementCardMap;

    public Deck() {
        // initialize the deck
        cards = new ArrayList<>();
        initializeCardEffectMap();

        elementCardMap = new LinkedHashMap<>();

        elementCardMap.put("Fire", new String[]{"Flame Burst", "Inferno Strike", "Blazing Spear", "Ember Storm", "Molten Lava"});
        elementCardMap.put("Water", new String[]{"Tidal Wave", "Aqua Slash", "Riptide", "Healing Rain", "Water Shield"});
        elementCardMap.put("Earth", new String[]{"Rock Slide", "Earthquake", "Boulder Throw", "Mountain Guard", "Sandstorm"});
        elementCardMap.put("Air", new String[]{"Gale Force", "Cyclone", "Whirlwind", "Air Blast", "Breeze of Life"});
        elementCardMap.put("Lightning", new String[]{"Thunderbolt", "Electroshock", "Lightning Strike", "Static Charge", "Paralyze"});

        // populate the deck with cards
        // making copies of each card 30x (solution for now...)
        elementCardMap.forEach((element, cardNames) -> {
            for (String cardName : cardNames) {
                String effectType = cardEffectMap.getOrDefault(cardName, "Damage");
                for (int i = 0; i < 30; i++) {
                    Card card = new Card(cardName, element, generateCardValue(element), effectType);
                    cards.add(card);
                }
            }
        });
    }

    private void initializeCardEffectMap() {
        // map card names to their effect types
        cardEffectMap = new HashMap<>();
        cardEffectMap.put("Healing Rain", "Healing");
        cardEffectMap.put("Paralyze", "Skip another player's turn");

        // all other cards default to "damage" and are handled by getOrDefault in the Deck constructor
    }

    private int generateCardValue(String element) {
        // generate different ranges of values based on the element
        switch (element.toLowerCase()) {
            case "fire":
                return randomValue(40, 60);
            case "water":
                return randomValue(20, 50);
            case "earth":
                return randomValue(30, 50);
            case "air":
                return randomValue(10, 40);
            case "lightning":
                return randomValue(50, 70);
            default:
                return randomValue(10, 60);
        }
    }

    private int randomValue(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public int getCardCount() {
        return cards.size();
    }

    public Card takeCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}

