import database.dbService;
import model.*;
import model.exceptions.InvalidPointException;
import view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) throws InvalidPointException {
        final String name1 = "Vovchik";
        final String name2 = "Vovan";

        DbPlayer player1 = new DbPlayer();
        DbPlayer player2 = new DbPlayer();

        dbService dbService = new dbService();
        player1 = dbService.getPlayer(name1);
        player2 = dbService.getPlayer(name2);

        final Player[] players = new Player[2];
        players[0] = new Player(player1.getName(), player1, Figure.X);
        players[1] = new Player(player2.getName(), player2, Figure.O);

        Field field = new Field(3);

        final Game game = new Game(players, field, "XO");
        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(game);
        while (consoleView.move(game)) {
            consoleView.show(game);
        }
        // TODO: 23.11.2020 add update table Game 

    }
}
