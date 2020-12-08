package model;

public class Game {

    private final Player[] players;

    private final Field field;

    private final String name;

    private final DbGame dbGame;

    public Game(Player[] players, Field field, String name, DbGame dbGame) {
        this.players = players;
        this.field = field;
        this.name = name;
        this.dbGame = dbGame;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public DbGame getDbGame() {
        return dbGame;
    }

}
