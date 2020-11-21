import model.Field;
import model.Figure;
import model.Game;
import model.Player;
import model.exceptions.InvalidPointException;
import view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) throws InvalidPointException {
        final String name1 = "Vovchik";
        final String name2 = "Vovan";

        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        Field field = new Field(3);
//        field.setFigure(new Point(0,0), Figure.X);
//        field.setFigure(new Point(0,1), Figure.O);
//        field.setFigure(new Point(1,2), Figure.X);
//        field.setFigure(new Point(1,0), Figure.O);
//        field.setFigure(new Point(2,0), Figure.X);
//        field.setFigure(new Point(2,1), Figure.O);


        final Game game = new Game(players, field, "XO");
        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(game);
        while (consoleView.move(game)) {
            consoleView.show(game);
        }

    }
}
