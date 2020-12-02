import database.dbService;
import model.*;
import model.exceptions.InvalidPointException;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class XOCLI {

    public static void main(final String[] args) throws InvalidPointException, IOException, SQLException {

        final ConsoleView consoleView = new ConsoleView();

        String player1Name = consoleView.askName(" first ");
        String player2Name = consoleView.askName(" second ");

        DbPlayer player1 = new DbPlayer();
        DbPlayer player2 = new DbPlayer();

        dbService dbService = new dbService();
        player1 = dbService.getPlayer(player1Name);
        player2 = dbService.getPlayer(player2Name);

        final Player[] players = new Player[2];
        players[0] = new Player(player1.getName(), player1, Figure.X);
        players[1] = new Player(player2.getName(), player2, Figure.O);

        Field field = new Field(3);

        final Game game = new Game(players, field, "XO");
        consoleView.show(game);
        while (consoleView.move(game)) {
            consoleView.show(game);
        }


    }
}
