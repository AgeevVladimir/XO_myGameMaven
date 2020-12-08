package model;

import com.google.common.base.Objects;

public class DbGame {

    private int id;

    private String name;

    private int player1Id;

    private int player2Id;

    private int winnerId;

    public DbGame(int id, String name, int player1Id, int player2Id, int winnerId) {
        this.id = id;
        this.name = name;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
    }

    public DbGame() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("player1Id", player1Id)
                .add("player2Id", player2Id)
                .add("winnerId", winnerId)
                .toString();
    }
}
