package com.dudarev.island;


import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.utils.BoardInitializer;
import com.dudarev.island.classes.utils.MovementManager;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello App");

        Board board = new Board(5, 5);
        MovementManager movementManager = new MovementManager(board);
        BoardInitializer boardInitializer = new BoardInitializer();
        try {
            boardInitializer.initBoard(movementManager);
        } catch (Exception e) {
            System.out.println(e);
        }

        board.printScheme();
    }
}
