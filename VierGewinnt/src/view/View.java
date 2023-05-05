package view;

import controller.Controller;
import model.Player;
import processing.core.PApplet;


public class View extends PApplet {

    public static void main(String[] args) {
        PApplet.main(View.class);
    }

    public int width = 1000;
    public int height = 700;

    public int diskPosMultiplier = 100;
    public int heightBoarder = 100;
    public int widthBoarder = 125;
    public int radius = 60;


    Controller controller = new Controller(this);


    public void settings() {
        size(width, height);
    }

    public void setup() {
        background(0);
    }


    public void draw() {
        clear();
        controller.nextFrame();
    }


    public void showWinner(Player currentPlayer, boolean isWinning) {
        textSize(20);
        if (isWinning) {
            if (currentPlayer == Player.PLAYER_1) {
                fill(255, 0, 0);
                text("Player 1!", width / 2f, height / 2f);
            } else if (currentPlayer == Player.PLAYER_2) {
                fill(0, 255, 0);
                text("Player 2!", width / 2f, height / 2f);
            }
        } else {
            fill(255);
            text("No Player won! - Draw", width / 2f, height / 2f);
        }
        textSize(1);
    }

    public void drawGrid(int[][] grid, Player currentPlayer) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Player.PLAYER_1.getId()) {
                    fill(255, 0, 0);
                } else if (grid[i][j] == Player.PLAYER_2.getId()) {
                    fill(0, 255, 0);
                } else {
                    fill(255);
                }
                circle(j * diskPosMultiplier + heightBoarder, i * diskPosMultiplier + widthBoarder, radius);
            }
        }
        drawHover(currentPlayer);
    }

    @Override
    public void mouseClicked() {
            int column = evaluateColumn();
                controller.userInput(column);
    }


    private void drawHover(Player currentPlayer) {
        int column = evaluateColumn();
        if (controller.isColumnValid(column) && !controller.isRowFull(column)) {
            int xValue = (column * 100) + 100;
            rectMode(CENTER);
            noFill();
            if (currentPlayer == Player.PLAYER_1)

                stroke(0, 255, 0);

            else
                stroke(255, 0, 0);

            rect(xValue, 350, 100, 675);
            stroke(0, 0, 0);
            fill(0);
        }
    }

    private int evaluateColumn() {
        int leftHand = ((((mouseX - 100) + 30 + 25) - 100) / diskPosMultiplier);
        int rightHand = (((mouseX - 100) + 30 + 25) / diskPosMultiplier);
        return controller.isColumnValid(leftHand) ? rightHand : leftHand;
    }


}




