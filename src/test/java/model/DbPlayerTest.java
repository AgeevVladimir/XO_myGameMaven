package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbPlayerTest {

    @Test
    void getName() {

        final String inputValue = "Vovchik";
        final String expectedValue = inputValue;

        final DbPlayer player = new DbPlayer(1, inputValue, 1, 1);

        final String actlValue = player.getName();

        assertEquals(expectedValue, actlValue);
    }
}