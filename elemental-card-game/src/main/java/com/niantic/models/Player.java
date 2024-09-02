package com.niantic.models;


public class Player {
    private String playerName;
    private int hp;
    private Hand hand;
    private boolean isParalyzed;
    private String playerElement;

    public Player(String playerName) {
        this.playerName = playerName;
        this.hp = 100;
        this.hand = new Hand();
        this.isParalyzed = false;
        this.playerElement = Element.getRandomElement();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHP() {
        return hp;
    }

    public int adjustHP(int amount) {
        hp += amount;
        if (hp < 0) {
            hp = 0;
        }
        return hp;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isParalyzed() {
        return isParalyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        isParalyzed = paralyzed;
    }

    public void dealTo(Card card) {
        hand.addCard(card);
    }

    public String getPlayerElement() {
        return playerElement;
    }

    public void playCard(Card card, Player target) {
        String effectType = card.getEffectType();
        int cardValue = card.getCardValue();
        int adjustedValue = cardValue;


        // checking for elemental strengths and weaknesses
        if (effectType.equals("Damage")) {
            if (Element.isStrongAgainst(this.getPlayerElement(), target.getPlayerElement())) {
                adjustedValue = (int) (cardValue * 1.5); // increase value if strong against
                System.out.println(this.getPlayerName() + "'s " + this.getPlayerElement() + " is strong against " + target.getPlayerElement() + "!");
            } else if (Element.isWeakAgainst(this.getPlayerElement(), target.getPlayerElement())) {
                adjustedValue = (int) (cardValue * 0.5); // decrease value if weak against
                System.out.println(this.getPlayerName() + "'s " + this.getPlayerElement() + " is weak against " + target.getPlayerElement() + "!");
            }
        }

        switch (effectType) {
            case "Damage":
                target.adjustHP(-adjustedValue);  // decrease target's HP by card value
                System.out.println(target.getPlayerName() + " takes " + adjustedValue + " damage.");
                break;

            case "Healing":
                target.adjustHP(card.getCardValue());  // increase target's HP by card value
                System.out.println(target.getPlayerName() + " heals " + card.getCardValue() + " HP.");
                break;

            case "Paralyze":
                target.setParalyzed(true);  // paralyze the target, making them skip their next turn
                System.out.println(target.getPlayerName() + " is paralyzed and will skip their next turn.");
                break;

            default:
                System.out.println("Unknown effect type.");
        }

        hand.removeCard(card);
    }

}
