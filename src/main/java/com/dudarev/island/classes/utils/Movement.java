package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Movement {
    Board board;

    public Movement(Board simulationBoard) {
        board = simulationBoard;
    }

    public int getLeftBoundX() {
        return board.getLeftBoundX();
    }

    public int getRightBoundX() {
        return board.getRightBoundX();
    }

    public int getUpBoundY() {
        return board.getUpBoundY();
    }

    public int getDownBoundY() {
        return board.getDownBoundY();
    }


    enum Directions {
        Up, Right, Down, Left
    }

    private Directions getInitialDirectionToStartFrom() {
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

    private Directions getNextDirectionClockWise(Directions currDirection) {
        ArrayList<Directions> directionsArrayList = new ArrayList<>(Arrays.asList(Directions.values()));
        int currPosIndex = directionsArrayList.indexOf(currDirection);
        int nextPosIndex = currPosIndex + 1 >= directionsArrayList.size() ? 0 : currPosIndex + 1;
        return directionsArrayList.get(nextPosIndex);
    }

    private boolean canMoveThisDirection(Coords currCoords, Directions desirableDirection) {
        int currX = currCoords.getX();
        int currY = currCoords.getY();

        if (desirableDirection == Directions.Up && currY != getUpBoundY()) {
            return true;
        } else if (desirableDirection == Directions.Right && currX != getRightBoundX()) {
            return true;
        } else if (desirableDirection == Directions.Down && currY != getDownBoundY()) {
            return true;
        } else if (desirableDirection == Directions.Left && currX != getLeftBoundX()) {
            return true;
        } else return false;
    }

    private static Coords getUpdatedCoords(Coords curr, Directions direction) {
        if (direction == Directions.Up) {
            return new Coords(curr.getX(), curr.getY() - 1);
        } else if (direction == Directions.Right) {
            return new Coords(curr.getX() + 1, curr.getY());
        } else if (direction == Directions.Down) {
            return new Coords(curr.getX(), curr.getY() + 1);
        } else if (direction == Directions.Left) {
            return new Coords(curr.getX() - 1, curr.getY());
        } else return curr;
    }

    private Coords getNextCoords(Animal animal) {
        Coords currCoords = animal.getCell().getCoords();
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

        return getUpdatedCoords(currCoords, resultDirection);
    }

    public Coords move(Animal animal) {
        Coords animalNextCoords = getNextCoords(animal);
        board.moveSimulationItem(animal, animalNextCoords);

        return animal.getCell().getCoords();
    }


}
