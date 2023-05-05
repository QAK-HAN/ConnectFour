package model;

import java.util.Arrays;
public class Model {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    private static final int[][] grid = new int[ROWS][COLUMNS];
    private Player currentPlayer = Player.PLAYER_1;

    public Model() {}



    public static void main(String[] args) {
        // initialize board
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = ' ';

            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    private Player invertPlayer(Player player) {
        if (player == Player.PLAYER_1)
            return Player.PLAYER_2;
        else
            return Player.PLAYER_1;
    }
    public int[][] getGrid() {
        return Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);
    }
    public boolean isGameOver(){

        return isWinning(Player.PLAYER_1) || isWinning(Player.PLAYER_2) || isBoardFull();
    }
    public boolean isWinning(Player player) {
        return isWinningHorizontally(player) || isWinningVertically(player) || isWinningDiagonally(player);
    }
    private boolean isBoardFull() {
        return Arrays.stream(grid).allMatch(row -> Arrays.stream(row).allMatch(col -> col != Player.PLAYER_NONE.getId()));
    }
    private boolean isWinningDiagonally(Player player) {
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[0].length - 3; j++) {
                if (grid[i][j] == grid[i + 1][j + 1] && grid[i + 1][j + 1] == grid[i + 2][j + 2] && grid[i + 2][j + 2] == grid[i + 3][j + 3] && grid[i][j] == player.getId()) {
                    return true;
                }
            }
        }
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 3; j < grid[0].length; j++) {
                if (grid[i][j] == grid[i + 1][j - 1] && grid[i + 1][j - 1] == grid[i + 2][j - 2] && grid[i + 2][j - 2] == grid[i + 3][j - 3] && grid[i][j] == player.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isWinningVertically(Player player) {
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == grid[i + 1][j] && grid[i + 1][j] == grid[i + 2][j] && grid[i + 2][j] == grid[i + 3][j] && grid[i][j] == player.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isWinningHorizontally(Player player) {
        for (int[] ints : this.grid) {
            for (int j = 0; j < this.grid[0].length - 3; j++) {
                if (ints[j] == ints[j + 1] && ints[j + 1] == ints[j + 2] && ints[j + 2] == ints[j + 3] && ints[j] == player.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isRowFull(int column) {
        var result = Arrays.stream(grid).map(row -> {
            for(int i = 0; i < row.length; i++) {
                if(column == i && row[i] == Player.PLAYER_NONE.getId())
                    return false;
            }
            return true;
        }).findFirst();
        return result.orElse(true);
    }
    public void updateGridField(int column) {
        if (!isGameOver()) {

        if (isGridClickable(column)) {
            currentPlayer = invertPlayer(currentPlayer);
            for (int row = grid.length - 1; row >= 0; row--)
                if (grid[row][column] == Player.PLAYER_NONE.getId()) {
                    grid[row][column] = currentPlayer.getId();
                    break;
                }
            for (int[] ints : grid) {
                System.out.println(Arrays.toString(ints));

            }
        }
        }
    }
    public boolean isColumnValid(int column) {
        return column >= 0 && column <= 6;
    }
    public boolean isGridClickable(int column) {
        return isColumnValid(column) && !isRowFull(column);
    }





}
