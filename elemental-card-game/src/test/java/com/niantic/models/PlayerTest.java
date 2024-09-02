package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Karen");
    }

    @Test
    public void getPlayerName_shouldReturn_playerName() {
        String expectedName = "Karen";
        String actualName = player.getPlayerName();
        assertEquals(expectedName, actualName);

    }

    @Test
    public void getHp_shouldReturn_playerHp() {
        int expectHp = 100;
        int actualHp = player.getHP();
        assertEquals(expectHp, actualHp);
    }

    @Test
    public void increaseHp_shouldReturn_playerAdjustedHp() {
        int initialHp = player.getHP();
        int amountIncreased = 20;
        int expectedHp = initialHp + amountIncreased;

        int totalHp = player.adjustHP(amountIncreased);

        assertEquals(expectedHp, totalHp);
    }

    @Test
    public void decreaseHp_shouldReturn_playerAdjustedHp() {
        int initialHp = player.getHP();
        int amountDecreased = -40;
        int expectedHp = initialHp + amountDecreased;

        int totalHp = player.adjustHP(amountDecreased);

        assertEquals(expectedHp, totalHp);
    }


}
