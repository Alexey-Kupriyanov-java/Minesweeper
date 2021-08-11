package minesweeper;

public class Cell {
    private boolean mine;
    private boolean mark;
    private boolean explored;
    private int minesAround;

    public boolean isExplored() {
        return explored;
    }

    public void setExplored() {
        this.explored = true;
        mark = false;
    }

    public boolean isNumber() {
        return !mine && minesAround > 0;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine() {
        mine = true;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark() {
        mark = !mark;
    }

    public void incrementMinesAround() {
        this.minesAround++;
    }

    @Override
    public String toString() {
        if (mark) {
            return "*";
        }
        if (explored) {
            if (mine) {
                return "X";
            } else if (minesAround > 0) {
                return String.valueOf(minesAround);
            } else {
                return "/";
            }
        } else {
            return ".";
        }
    }
}
