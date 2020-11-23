package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void getName() {

        final String inputValue = "Vovchik";
        final String expectedValue = inputValue;


        final Player player = new Player(inputValue, new DbPlayer(), null);

        final String actlValue = player.getName();

        assertEquals(expectedValue, actlValue);

    }

    @Test
    public void getFigure() {

        final Figure inputValue = Figure.O;
        final Figure expectedValue = inputValue;

        final Player player = new Player(null, new DbPlayer(), inputValue);

        final Figure actualValue = player.getFigure();

        assertEquals(expectedValue, actualValue);

    }
}