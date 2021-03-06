package database;

import model.DbGame;
import model.DbPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbService {

    public ResultSet callExecuteQuery(String sql) {


        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "vova", "123");

            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
//            System.out.println(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
//        System.out.println("sql statement successfully executed");

        return rs;
    }


    public void callExecuteUpdate(String sql) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "vova", "123");

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
//            System.out.println(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

//        System.out.println("table successfully updated");

    }



    public DbPlayer getPlayer(String name) throws SQLException {

        DbPlayer player = new DbPlayer();

        String sql = "SELECT * FROM xo_game.player p where p.name = '" + name + "'";

        ResultSet rs = callExecuteQuery(sql);

        if (rs == null) {
            System.out.println("Error in executing query");
            return null;

        } else {
            try {

                List<DbPlayer> playerFromResultSet = getPlayerFromResultSet(rs);

                if (playerFromResultSet.isEmpty()) {return null;}
                    else {return playerFromResultSet.get(0);}

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }

        }
    }

    public void updatePlayer(DbPlayer dbPlayer) {

        String sql = "UPDATE xo_game.player SET X_WIN_NUM =" + dbPlayer.getX_win_num() + ","
                                               + " O_WIN_NUM =" + dbPlayer.getO_win_num()
                                               + " WHERE NAME = " + "'" + dbPlayer.getName() + "'";

        callExecuteUpdate(sql);

    }

    public void addPlayer(String playerName) {

        String sql = "INSERT INTO xo_game.player (NAME, X_WIN_NUM, O_WIN_NUM) VALUES"
                + "(" + "'" + playerName + "'" + "," + 0 + "," + 0 + ")";

        callExecuteUpdate(sql);
    }

    private List<DbPlayer> getPlayerFromResultSet(ResultSet rs) throws SQLException {

        List<DbPlayer> dbPlayers = new ArrayList<>();

        while(rs.next()) {

            int id = rs.getInt("ID");
            String playername = rs.getString("NAME");
            int x_win_num = rs.getInt("X_WIN_NUM");
            int o_win_num = rs.getInt("O_WIN_NUM");

            if (playername != null) {

                DbPlayer player = new DbPlayer();
                player.setId(id);
                player.setName(playername);
                player.setO_win_num(o_win_num);
                player.setX_win_num(x_win_num);

                dbPlayers.add(player);

            }
        }
        return dbPlayers;

    }


    public void addGame(DbPlayer player1, DbPlayer player2) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String gameName = "Game at " + timestamp.toString().substring(0,16);

        String sql = "INSERT INTO xo_game.game (NAME, PLAYER1_ID, PLAYER2_ID) VALUES"
                + "(" + "'" + gameName + "'" + "," + player1.getId() + "," + player2.getId() + ")";

        callExecuteUpdate(sql);

    }

    public void updateGame(DbGame game) {

        String sql = "UPDATE xo_game.game SET WINNER_ID =" + game.getWinnerId()
                + " WHERE ID = (SELECT max(id) FROM xo_game.game)";

        callExecuteUpdate(sql);

    }
}
