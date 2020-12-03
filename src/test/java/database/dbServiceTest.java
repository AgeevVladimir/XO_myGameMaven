package database;

import model.DbPlayer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class dbServiceTest {

    @Test
    void getPlayer() throws SQLException {

        dbService dbService = new dbService();

        String expectedPlayerName = "Vova";

        DbPlayer player = dbService.getPlayer(expectedPlayerName);

        System.out.println(player);

        assertEquals(expectedPlayerName, player.getName());

    }

    @Test
    void getPlayerWhenNoSuchNameInDB() throws SQLException {

        dbService dbService = new dbService();

        String expectedPlayerName = "Vladimir";

        DbPlayer player = dbService.getPlayer(expectedPlayerName);

        System.out.println(player);

        assertNull(player.getName());

    }
}