package com.dudarev.island.classes.tests;

import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.predators.Wolf;
import com.dudarev.island.classes.utils.Coords;
import com.dudarev.island.classes.utils.Movement;


import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RED;
import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RESET;

public class MovementTests {

    private Movement movement;

    private boolean areNewCoordsWrong(Coords newCoords) {
        int newX = newCoords.getX();
        int newY = newCoords.getY();

        return newX < movement.getLeftBoundX() ||
                newX > movement.getRightBoundX() ||
                newY < movement.getUpBoundY() ||
                newY > movement.getDownBoundY();
    }

    private String getExceptionMessage(String testName, int x, int y) {
        return testName + " starts with " + x + "," + y + " coords got wrong new Coords";
    }

    private void printStartTestMessage(String testName, int x, int y) {
        System.out.println(testName + " starts with " + x + "," + y + " coords");
    }

    private void printEndTestMessage(String testName, Coords from, Coords to) {
        System.out.println("Wolf moved from starts from " + from + " coords to " + to);
        System.out.println(testName + " end");
        System.out.println("-----------------------------------------");
    }

    public void checkWolfCanMove1() throws Exception {
        int leftBoundX = movement.getLeftBoundX();
        int upBoundY = movement.getUpBoundY();

        printStartTestMessage("checkWolfCanMove1", leftBoundX, upBoundY);

        Wolf wolf = new Wolf();
        wolf.linkToCell(new Board.Cell(leftBoundX, upBoundY));
        movement.move(wolf);
        Coords startCoords = wolf.getCell().getCoords();
        Coords newCoords = movement.move(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove1", leftBoundX, upBoundY));
        }

        printEndTestMessage("checkWolfCanMove1", startCoords, newCoords);
    }

    public void checkWolfCanMove2() throws Exception {
        int rightBoundX = movement.getRightBoundX();
        int upBoundY = movement.getUpBoundY();

        printStartTestMessage("checkWolfCanMove2", rightBoundX, upBoundY);

        Wolf wolf = new Wolf();
        wolf.linkToCell(new Board.Cell(rightBoundX, upBoundY));
        movement.move(wolf);
        Coords startCoords = wolf.getCell().getCoords();
        Coords newCoords = movement.move(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove2", rightBoundX, upBoundY));
        }

        printEndTestMessage("checkWolfCanMove2", startCoords, newCoords);
    }

    public void checkWolfCanMove3() throws Exception {
        int rightBoundX = movement.getRightBoundX();
        int downBoundY = movement.getDownBoundY();

        printStartTestMessage("checkWolfCanMove3", rightBoundX, downBoundY);

        Wolf wolf = new Wolf();
        wolf.linkToCell(new Board.Cell(rightBoundX, downBoundY));
        movement.move(wolf);
        Coords startCoords = wolf.getCell().getCoords();
        Coords newCoords = movement.move(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove3", rightBoundX, downBoundY));
        }

        printEndTestMessage("checkWolfCanMove3", startCoords, newCoords);
    }

    public void checkWolfCanMove4() throws Exception {
        int leftBoundX = movement.getLeftBoundX();
        int downBoundY = movement.getDownBoundY();

        printStartTestMessage("checkWolfCanMove4", leftBoundX, downBoundY);

        Wolf wolf = new Wolf();
        wolf.linkToCell(new Board.Cell(leftBoundX, downBoundY));
        movement.move(wolf);
        Coords startCoords = wolf.getCell().getCoords();
        Coords newCoords = movement.move(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove4", leftBoundX, downBoundY));
        }

        printEndTestMessage("checkWolfCanMove4", startCoords, newCoords);
    }

    public void checkWolfMoveFromCellToCell() {
        Board board = new Board(3, 3);
        Movement movement = new Movement(board);
        Wolf wolf = new Wolf();

        wolf.linkToCell(new Board.Cell(0, 0));

        for (int i = 0; i < 10; i++) {
            board.printScheme();
            movement.move(wolf);
            System.out.println("-----------------------------------");

        }
    }

    public void runTests() {
        movement = new Movement(new Board(3, 3));

        try {
            checkWolfCanMove1();
            checkWolfCanMove2();
            checkWolfCanMove3();
            checkWolfCanMove4();
            checkWolfMoveFromCellToCell();
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Test " + e.getMessage() + " failed" + ANSI_RESET);
        }

    }
}
