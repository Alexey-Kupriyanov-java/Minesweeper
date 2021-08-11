package minesweeper;

import java.util.Random;

public class Field {
    private final Cell[][] cell;
    private final int size;
    private final int cntMines;
    private String state;
    private String win;

    public Field(int size, int cntMines) {
        this.size = size;
        this.cntMines = cntMines;
        this.state = "game";
        this.cell = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell[i][j] = new Cell();
            }
        }
        //setMines(cntMines);
    }

    public void exploreCell(int row, int col) {
        Cell activeCell = cell[row][col];
        if (activeCell.isMine()) {
            showMines();
            setState("failed");
        }
        activeCell.setExplored();
        if (!activeCell.isNumber()) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < size && j >= 0 && j < size) {
                        if (!cell[i][j].isExplored()) {
                            exploreCell(i, j);
                        }
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < size; j++) {
                System.out.print(cell[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    public Cell getCell(int row, int col) {
        return cell[row][col];
    }

    public void checkWin() {
        int cntMineMarks = 0;
        int cntUnExplored = 0;
        int cntAllMarks = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cell[i][j].isMark()) {
                    cntAllMarks++;
                    if (cell[i][j].isMine()) {
                        cntMineMarks++;
                    }
                } else if (!cell[i][j].isExplored()) {
                    cntUnExplored++;
                }
            }
        }

        if (cntAllMarks == cntMines && cntMineMarks == cntMines ||
                cntUnExplored == cntMines) {
            setState("win");
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMines(int excludeRow, int excludeCol) {
        Random rnd = new Random();
        int cntSetMines = 0;
        while (cntSetMines < cntMines) {
            int row = rnd.nextInt(size);
            int col = rnd.nextInt(size);
            if (!cell[row][col].isMine() && !(row == excludeRow && col == excludeCol)) {
                cell[row][col].setMine();
                cntSetMines++;
                updateAroundCells(row, col);
            }
        }
    }

    private void updateAroundCells(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size) {
                    cell[i][j].incrementMinesAround();
                }
            }
        }

    }

    private void showMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cell[i][j].isMine()) {
                    cell[i][j].setExplored();
                }
            }
        }
    }
}
