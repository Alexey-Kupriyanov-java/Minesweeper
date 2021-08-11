package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        Game game = new Game(9, scanner.nextInt());
        game.start(scanner);
        scanner.close();
    }
}
