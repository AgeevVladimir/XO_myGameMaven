package database;

import model.DbPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dbServiceTest {

    @Test
    void getPlayer() {

        dbService dbService = new dbService();

        String expectedPlayerName = "Vova";

        DbPlayer player = dbService.getPlayer(expectedPlayerName);

        System.out.println(player);

        assertEquals(expectedPlayerName, player.getName());

    }
}