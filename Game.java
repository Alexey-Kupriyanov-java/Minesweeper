package minesweeper;

import java.util.Scanner;

public class Game {
    Field field;
    boolean gameOver;
    int step;

    public Game(int size, int cntMines) {
        this.field = new Field(size, cntMines);
    }

    public void start(Scanner scanner) {
        field.print();
        while (!gameOver) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            int col = scanner.nextInt() - 1;
            int row = scanner.nextInt() - 1;
            String command = scanner.next();
            Cell activeCell = field.getCell(row, col);
            if (activeCell.isNumber() && activeCell.isExplored()) {
                System.out.println("There is a number here!");
                continue;
            }
            if (command.equals("mine")) {
                activeCell.setMark();
            } else if (command.equals("free")) {
                if (step++ == 0) {
                    field.setMines(row, col);
                }
                field.exploreCell(row, col);
                field.checkWin();
            }
            field.print();
            if (field.getState().equals("win")) {
                System.out.println("Congratulations! You found all the mines!");
                gameOver = true;
            } else if (field.getState().equals("failed")) {
                System.out.println("You stepped on a mine and failed!");
                gameOver = true;
            }
        }
    }
}
