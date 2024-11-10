package com.niantic.application;

import com.niantic.models.*;
import com.niantic.ui.ColorCodes;

import java.util.*;

public class CardGameApplication {
    Deck deck = new Deck();
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> activePlayers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void run() {
        addPlayers();
        dealCards();
        displayAllPlayerDetails();
        while (!isGameOver()) {
            for (Player player : new ArrayList<>(activePlayers)) {
                if (player.getHP() > 0) {
                    takeTurn(player);
                    if (isGameOver()) break;
                }
            }
        }
        declareWinner();
    }

    private void dealCards() {
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            for (Player player : activePlayers) {
                Card card = deck.takeCard();
                player.dealTo(card);
            }
        }
    }

    private void addPlayers() {
        players.add(new Player("Karen"));
        players.add(new Player("Valerie"));
        players.add(new Player("Thabata"));
        players.add(new Player("Gregor"));
        players.add(new Player("Robin"));

        activePlayers.addAll(players);

    }

    private void displayAllPlayerDetails() {
        System.out.println(ColorCodes.BOLD + "Current Player Details:" + ColorCodes.RESET);
        for (Player player : activePlayers) {
            String element = player.getPlayerElement();
            String color = ColorCodes.getElementColor(element);

            String strongAgainst = Element.getStrongAgainst(element);
            String weakAgainst = Element.getWeakAgainst(element);

            System.out.println(color + player.getPlayerName() + ColorCodes.RESET);
            System.out.println("  Element: " + color + element + ColorCodes.RESET);
            System.out.println("  Strong Against: " + ColorCodes.getElementColor(strongAgainst) + strongAgainst + ColorCodes.RESET);
            System.out.println("  Weak Against: " + ColorCodes.getElementColor(weakAgainst) + weakAgainst + ColorCodes.RESET);
            System.out.println("  HP: " + player.getHP());
            System.out.println();
        }
    }

    private void takeTurn(Player player) {
        String playerColor = ColorCodes.getElementColor(player.getPlayerElement());
        System.out.println(playerColor + player.getPlayerName() + "'s turn:" + ColorCodes.RESET);
        System.out.println("HP: " + player.getHP());
        System.out.println("Cards:");

        ArrayList<Card> cards = player.getHand().getCards();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            System.out.println((i + 1) + ". " + card.getCardElement() + " - " + card.getCardName() +
                    ": " + card.getCardValue() + " (" + card.getEffectType() + ")");
        }

        System.out.print("Choose a card to play (enter the number): ");
        int cardIndex = scanner.nextInt() - 1;
        Card selectedCard = cards.get(cardIndex);

        System.out.println("Choose a target player:");
        for (int i = 0; i < activePlayers.size(); i++) {
            Player target = activePlayers.get(i);
            System.out.println((i + 1) + ". " + target.getPlayerName() + " (HP: " + target.getHP() + ")");
        }

        System.out.print("Enter number of target: ");
        int targetIndex = scanner.nextInt() - 1;
        Player targetPlayer = activePlayers.get(targetIndex);

        player.playCard(selectedCard, targetPlayer);
        int adjustedHP = targetPlayer.getHP();

        System.out.println(player.getPlayerName() + " used " + selectedCard.getCardName() + " on " + targetPlayer.getPlayerName() + "!");
        System.out.println(targetPlayer.getPlayerName() + " now has " + adjustedHP + " HP.");

        if (adjustedHP <= 0) {
            System.out.println(ColorCodes.RED + targetPlayer.getPlayerName() + " has been defeated and is removed from the game." + ColorCodes.RESET);
            activePlayers.remove(targetPlayer);
        }

        System.out.println();
    }

    private boolean isGameOver() {
        return activePlayers.size() <= 1;
    }

    private void declareWinner() {
        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
            String winnerColor = ColorCodes.getElementColor(winner.getPlayerElement());
            System.out.println(winnerColor + winner.getPlayerName() + " wins the game!" + ColorCodes.RESET);
        } else {
            System.out.println(ColorCodes.BOLD + ColorCodes.GRAY + "No winner. The game ended in a draw." + ColorCodes.RESET);
        }
    }
}
