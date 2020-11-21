package database;

import model.DbPlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dbService {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        String sql = "";

        List<DbPlayer> players= new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "vova", "123");

            stmt = c.createStatement();

            sql = "SELECT * FROM xo_game.player";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int x_win_num = rs.getInt("X_WIN_NUM");
                int o_win_num = rs.getInt("O_WIN_NUM");
                System.out.println(id + ", " + name + ", " + x_win_num +
                        ", " + o_win_num);
                DbPlayer player = new DbPlayer();
                player.setId(id);
                player.setName(name);
                player.setO_win_num(o_win_num);
                player.setX_win_num(x_win_num);

                players.add(player);

            }

            for (DbPlayer item : players){
                System.out.println(item);
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        System.out.println("successfully selected");
    }

}