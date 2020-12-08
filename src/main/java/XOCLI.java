import database.dbService;
import model.*;
import model.exceptions.InvalidPointException;
import view.ConsoleView;

import java.io.IOException;
import java.sql.SQLException;

public class XOCLI {

    public static void main(final String[] args) throws InvalidPointException, IOException, SQLException {

        final ConsoleView consoleView = new ConsoleView();

        String player1Name = consoleView.askName(" first ");
        String player2Name = player1Name;
        while (player2Name.equals(player1Name)) {
            player2Name = consoleView.askName(" second ");
        }

        dbService dbService = new dbService();
        DbPlayer player1 = dbService.getPlayer(player1Name);
        DbPlayer player2 = dbService.getPlayer(player2Name);

        final Player[] players = new Player[2];
        players[0] = new Player(player1.getName(), player1, Figure.X);
        players[1] = new Player(player2.getName(), player2, Figure.O);

        Field field = new Field(3);

        DbGame dbGame = new DbGame();

        dbService.addGame(player1, player2);

        final Game game = new Game(players, field, "XO", dbGame);

        consoleView.show(game);
        while (consoleView.move(game)) {
            consoleView.show(game);
        }


    }
}
