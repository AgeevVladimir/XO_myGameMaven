package model;

public class DbPlayer {

    private int id;

    private String name;

    private int x_win_num;

    private int o_win_num;

    public DbPlayer(int id, String name, int x_win_num, int o_win_num) {
        this.id = id;
        this.name = name;
        this.x_win_num = x_win_num;
        this.o_win_num = o_win_num;
    }

    public DbPlayer() {

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getX_win_num() {
        return x_win_num;
    }

    public int getO_win_num() {
        return o_win_num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX_win_num(int x_win_num) {
        this.x_win_num = x_win_num;
    }

    public void setO_win_num(int o_win_num) {
        this.o_win_num = o_win_num;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("x_win_num", x_win_num)
                .add("o_win_num", o_win_num)
                .toString();
    }
}
