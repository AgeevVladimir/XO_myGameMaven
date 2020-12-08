package view;

import controllers.CurrentMoveController;
import controllers.MoveAdvisorController;
import controllers.MoveController;
import controllers.WinnerController;
import database.dbService;
import model.DbPlayer;
import model.Field;
import model.Figure;
import model.Game;
import model.exceptions.AlreadyOccupiedException;
import model.exceptions.InvalidPointException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.printf("Game name:%s%n", game.getName());
        final Field field = game.getField();
        for (int x = 0; x < field.getSize(); x++) {
            printLine(field, x);
            if (x!=field.getSize()-1) printSeparator();
        }
    }

    public boolean move(final Game game) throws InvalidPointException {
        final Field field = game.getField();
        final Figure currentFigure = currentMoveController.currentMove(field);
        final Figure winner1 = winnerController.getWinner(field);
        final List<Point> availablePoints;
        final MoveAdvisorController moveAdvisorController = new MoveAdvisorController();
        final dbService dbService = new dbService();

        if (winner1 != null) {
            System.out.printf("winner is: %s\n", winner1);
            updatePlayerDb(game, winner1, dbService);
            return false;
        }
        if (currentFigure == null) {
            final Figure winner = winnerController.getWinner(field);
            if (winner == null) {
                System.out.println("no winner and no move left");
                return false;
            } else {
                System.out.printf("winner is: %s\n", winner);
                updatePlayerDb(game, winner, dbService);
                return false;
            }
        }

        availablePoints = moveAdvisorController.getAllAvailablePoints(field);

        System.out.println("Available points");

        for (Point item : availablePoints){
            if (item != null){
                System.out.print("X: " + item.x);
                System.out.println(", Y: " + item.y);
            }
        }

        System.out.printf("Please enter coordinate for figure %s", currentFigure);
        System.out.println();
        final Point point = askPoint();

        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (InvalidPointException | AlreadyOccupiedException e) {
            e.printStackTrace();
            System.out.println("Point os invalid!");
        }
        return true;
    }

    private void updatePlayerDb(Game game, Figure winner1, dbService dbService) {
        if (winner1 == Figure.X) {
            int x_win_num = game.getPlayers()[0].getDbPlayer().getX_win_num();
            game.getPlayers()[0].getDbPlayer().setX_win_num(x_win_num + 1);
            dbService.updatePlayer(game.getPlayers()[0].getDbPlayer());
        } else {
            int o_win_num = game.getPlayers()[1].getDbPlayer().getO_win_num();
            game.getPlayers()[1].getDbPlayer().setO_win_num(o_win_num + 1);
            dbService.updatePlayer(game.getPlayers()[1].getDbPlayer());
        }
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.printf("Enter coordinate %s", coordinateName);
        Scanner in = new Scanner(System.in);
        return in.nextInt();

    }


    private void printLine(final Field field, final int x) {

        for (int y = 0; y < field.getSize(); y++) {
            final Figure figure;
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            if (y!=0) System.out.print(" | ");
            System.out.print(figure != null ? figure : " ");
        }
        System.out.println();

    }

    private void printSeparator() {
        System.out.println("----------");
    }

    public String askName(String num) throws IOException, SQLException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter" + num + "player");
        String playerName = reader.readLine();

        dbService dbService = new dbService();

        while (dbService.getPlayer(playerName) == null) {

            System.out.println("Payer " + playerName + " doesn't exist. Press Y to create new player");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();
            if (answer == "Y") {
                dbService.addPlayer(playerName);
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter" + num + "player");
                playerName = reader.readLine();
            }
        }

        return dbService.getPlayer(playerName).getName();
    }

}
