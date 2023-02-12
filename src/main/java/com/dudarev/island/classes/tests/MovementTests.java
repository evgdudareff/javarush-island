package com.dudarev.island.classes.tests;

import com.dudarev.island.classes.predators.Wolf;
import com.dudarev.island.classes.utils.Coords;
import com.dudarev.island.classes.utils.Movement;

import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RED;
import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RESET;

public class MovementTests {

    private static boolean areNewCoordsWrong(Coords newCoords) {
        int newX = newCoords.getX();
        int newY = newCoords.getY();

        return newX < Movement.getLeftBoundX() || newX > Movement.getRightBoundX() || newY < Movement.getUpBoundY() || newY > Movement.getDownBoundY();
    }

    private static String getExceptionMessage(String testName, int x, int y) {
        return testName + " starts with " + x + "," + y + " coords got wrong new Coords";
    }

    private static void printStartTestMessage(String testName, int x, int y) {
       System.out.println(testName + " starts with " + x + "," + y + " coords");
    }

    private static void printEndTestMessage(String testName, Coords from, Coords to) {
        System.out.println("Wolf moved from starts from " + from + " coords to " + to);
        System.out.println(testName + " end");
        System.out.println("-----------------------------------------");
    }

    public static void checkWolfCanMove1() throws Exception {
        int leftBoundX = Movement.getLeftBoundX();
        int upBoundY = Movement.getUpBoundY();

        printStartTestMessage("checkWolfCanMove1", leftBoundX, upBoundY);

        Wolf wolf = new Wolf();
        wolf.setCoords(leftBoundX, upBoundY);
        Coords startCoords = wolf.getCoords();
        Coords newCoords = Movement.getNextCoords(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove1", leftBoundX, upBoundY));
        }

        printEndTestMessage("checkWolfCanMove1", startCoords, newCoords);
    }

    public static void checkWolfCanMove2() throws Exception {
        int rightBoundX = Movement.getRightBoundX();
        int upBoundY = Movement.getUpBoundY();

        printStartTestMessage("checkWolfCanMove2", rightBoundX, upBoundY);

        Wolf wolf = new Wolf();
        wolf.setCoords(rightBoundX, upBoundY);
        Coords startCoords = wolf.getCoords();
        Coords newCoords = Movement.getNextCoords(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove2", rightBoundX, upBoundY));
        }

        printEndTestMessage("checkWolfCanMove2", startCoords, newCoords);
    }

    public static void checkWolfCanMove3() throws Exception {
        int rightBoundX = Movement.getRightBoundX();
        int downBoundY = Movement.getDownBoundY();

        printStartTestMessage("checkWolfCanMove3", rightBoundX, downBoundY);

        Wolf wolf = new Wolf();
        wolf.setCoords(rightBoundX, downBoundY);
        Coords startCoords = wolf.getCoords();
        Coords newCoords = Movement.getNextCoords(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove3", rightBoundX, downBoundY));
        }

        printEndTestMessage("checkWolfCanMove3", startCoords, newCoords);
    }

    public static void checkWolfCanMove4() throws Exception {
        int leftBoundX = Movement.getLeftBoundX();
        int downBoundY = Movement.getDownBoundY();

        printStartTestMessage("checkWolfCanMove4", leftBoundX, downBoundY);

        Wolf wolf = new Wolf();
        wolf.setCoords(leftBoundX, downBoundY);
        Coords startCoords = wolf.getCoords();
        Coords newCoords = Movement.getNextCoords(wolf);

        if (areNewCoordsWrong(newCoords)) {
            throw new Exception(getExceptionMessage("checkWolfCanMove4", leftBoundX, downBoundY));
        }

        printEndTestMessage("checkWolfCanMove4", startCoords, newCoords);
    }

    public static void runTests() {

        try {
            checkWolfCanMove1();
            checkWolfCanMove2();
            checkWolfCanMove3();
            checkWolfCanMove4();
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Test " + e.getMessage() + " failed" + ANSI_RESET);
        }

    }
}
