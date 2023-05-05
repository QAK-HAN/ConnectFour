package model;

public enum Player {
    PLAYER_1(1),
    PLAYER_2(-1),
    PLAYER_NONE(0);

    Player(int i) {
        this.id = i;
    }
    private final int id;
    public int getId() {
        return id;
    }
}
