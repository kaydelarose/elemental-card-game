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

        if (effectType.equals("Damage")) {
            if (Element.isStrongAgainst(this.getPlayerElement(), target.getPlayerElement())) {
                adjustedValue = (int) (cardValue * 1.5);
                System.out.println(this.getPlayerName() + "'s " + this.getPlayerElement() + " is strong against " + target.getPlayerElement() + "!");
            } else if (Element.isWeakAgainst(this.getPlayerElement(), target.getPlayerElement())) {
                adjustedValue = (int) (cardValue * 0.5);
                System.out.println(this.getPlayerName() + "'s " + this.getPlayerElement() + " is weak against " + target.getPlayerElement() + "!");
            }
        }

        switch (effectType) {
            case "Damage":
                target.adjustHP(-adjustedValue);
                System.out.println(target.getPlayerName() + " takes " + adjustedValue + " damage.");
                break;

            case "Healing":
                target.adjustHP(card.getCardValue());
                System.out.println(target.getPlayerName() + " heals " + card.getCardValue() + " HP.");
                break;

            case "Paralyze":
                target.setParalyzed(true);
                System.out.println(target.getPlayerName() + " is paralyzed and will skip their next turn.");
                break;

            default:
                System.out.println("Unknown effect type.");
        }

        hand.removeCard(card);
    }

}
