package com.dudarev.island;


import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.utils.BoardInitializer;
import com.dudarev.island.classes.utils.Movement;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello App");

        Board board = new Board(3, 3);
        Movement movementUtils = new Movement(board);
        BoardInitializer boardInitializer = new BoardInitializer();
        boardInitializer.initBoard(movementUtils);
        board.printScheme();
    }
}
