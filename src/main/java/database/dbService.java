package database;

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

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        System.out.println("successfully selected");

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

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }



    public DbPlayer getPlayer(String name) {

        DbPlayer player = new DbPlayer();

        String sql = "SELECT * FROM xo_game.player p where p.name = '" + name + "'";

        System.out.println(sql);

        ResultSet rs = callExecuteQuery(sql);

        if (rs == null) {
            System.out.println("No player with name " + name);
            return null;
            // TODO: 23.11.2020 Create default player 'guest'

        } else {
            try {
                if (rs.next()) {

                    int id = rs.getInt("ID");
                    String playername = rs.getString("NAME");
                    int x_win_num = rs.getInt("X_WIN_NUM");
                    int o_win_num = rs.getInt("O_WIN_NUM");

                    player.setId(id);
                    player.setName(playername);
                    player.setO_win_num(o_win_num);
                    player.setX_win_num(x_win_num);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return player;
        }
    }


    public void updatePlayer(DbPlayer dbPlayer) {

        String sql = "UPDATE xo_game.player SET X_WIN_NUM =" + dbPlayer.getX_win_num() + ","
                                               + " O_WIN_NUM =" + dbPlayer.getO_win_num()
                                               + " WHERE NAME = " + "'" + dbPlayer.getName() + "'";
        System.out.println(sql);
        //Alternative: String sql = String.format("UPDATE xo_game.player p SET X_WIN_NUM = %s , O_WIN_NUM = %s WHERE NAME = %s", dbPlayer.getX_win_num(), dbPlayer.getO_win_num(), dbPlayer.getName());

        callExecuteUpdate(sql);

    }
}
