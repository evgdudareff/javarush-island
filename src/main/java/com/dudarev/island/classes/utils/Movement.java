package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Movement {
    private final static int leftBoundX = 0;
    private final static int rightBoundX = 2;
    private final static int upBoundY = 0;
    private final static int downBoundY = 2;

    public static int getLeftBoundX() {
        return leftBoundX;
    }

    public static int getRightBoundX() {
        return rightBoundX;
    }

    public static int getUpBoundY() {
        return upBoundY;
    }

    public static int getDownBoundY() {
        return downBoundY;
    }


    enum Directions {
        Up, Right, Down, Left
    }

    private static Directions getInitialDirectionToStartFrom() {
        int randomInt = new Random().nextInt(0, 100);
        if (randomInt >= 0 && randomInt < 25) {
            return Directions.Up;
        } else if (randomInt >= 25 && randomInt < 50) {
            return Directions.Right;
        } else if (randomInt >= 50 && randomInt < 75) {
            return Directions.Down;
        }
        return Directions.Left;
    }

    private static Directions getNextDirectionClockWise(Directions currDirection) {
        ArrayList<Directions> directionsArrayList = new ArrayList<>(Arrays.asList(Directions.values()));
        int currPosIndex = directionsArrayList.indexOf(currDirection);
        int nextPosIndex = currPosIndex + 1 >= directionsArrayList.size() ? 0 : currPosIndex + 1;
        return directionsArrayList.get(nextPosIndex);
    }

    private static boolean canMoveThisDirection(Coords currCoords, Directions desirableDirection) {
        int currX = currCoords.getX();
        int currY = currCoords.getY();

        if (desirableDirection == Directions.Up && currY != upBoundY) {
            return true;
        } else if (desirableDirection == Directions.Right && currX != rightBoundX) {
            return true;
        } else if (desirableDirection == Directions.Down && currY != downBoundY) {
            return true;
        } else if (desirableDirection == Directions.Left && currX != leftBoundX) {
            return true;
        } else return false;
    }

    private static Coords getNewCoords(Coords curr, Directions direction) {
        if (direction == Directions.Up) {
            return new Coords(curr.getX(), curr.getY() - 1);
        } else if (direction == Directions.Right) {
            return new Coords(curr.getX() + 1, curr.getY());
        } else if (direction == Directions.Down) {
            return new Coords(curr.getX(), curr.getY() + 1);
        } else if (direction == Directions.Left) {
            return new Coords(curr.getX() - 1, curr.getY());
        }
        else return curr;
    }

    public static Coords getNextCoords(Animal animal) {
        Coords currCoords = animal.getCoords();
        Directions currDesirableDirection = getInitialDirectionToStartFrom();
        Directions resultDirection = null;

        for (int i = 0; i < Directions.values().length - 1; i++) {
            if (canMoveThisDirection(currCoords, currDesirableDirection)) {
                resultDirection = currDesirableDirection;
                break;
            } else {
                currDesirableDirection = getNextDirectionClockWise(currDesirableDirection);
            }
        }

        if (resultDirection == null) {
            return currCoords;
        }

        return getNewCoords(currCoords, resultDirection);
    }


}
