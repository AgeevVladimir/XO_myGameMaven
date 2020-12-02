package model;

public class Player {

    private final String name;

    private final DbPlayer dbPlayer;

    private final Figure figure;

    public Player(final String name,
                  DbPlayer dbPlayer, final Figure figure) {
        this.name = name;
        this.dbPlayer = dbPlayer;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }

    public DbPlayer getDbPlayer() {return dbPlayer;}
}
