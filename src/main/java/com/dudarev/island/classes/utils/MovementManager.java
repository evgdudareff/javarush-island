package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.Herbivore;
import com.dudarev.island.classes.base.Predator;
import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MovementManager {
    Board board;

    public void linkWithBoard(Board board) {
        this.board = board;
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

    private boolean canMoveThisDirection(Animal animal, Directions desirableDirection) {
        Coords currCoords = animal.getCell().getCoords();
        int currX = currCoords.getX();
        int currY = currCoords.getY();

        if ((desirableDirection == Directions.Up && currY == getUpBoundY()) ||
                (desirableDirection == Directions.Right && currX == getRightBoundX()) ||
                (desirableDirection == Directions.Down && currY == getDownBoundY()) ||
                (desirableDirection == Directions.Left && currX == getLeftBoundX())
        ) {
            return false;
        }

        Board.Cell desirableCell = board.getCellByCoords(getShiftedByDirectionCoords(currCoords, desirableDirection));
        return desirableCell.getSimilarAnimalCount(animal) < animal.getMaxItemsPerCell();
    }

    private static Coords getShiftedByDirectionCoords(Coords curr, Directions direction) {
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
            if (canMoveThisDirection(animal, currDesirableDirection)) {
                resultDirection = currDesirableDirection;
                break;
            } else {
                currDesirableDirection = getNextDirectionClockWise(currDesirableDirection);
            }
        }

        if (resultDirection == null) {
            return currCoords;
        }

        return getShiftedByDirectionCoords(currCoords, resultDirection);
    }

    public Coords move(Animal animal) {
        Coords animalNextCoords = getNextCoords(animal);
        board.moveSimulationItem(animal, animalNextCoords);

        return animal.getCell().getCoords();
    }

    public void startLiveOneTickPredators(ArrayList<SimulationItem> predators) {

        int predatorsSize = predators.size();
        ArrayList<SimulationItem> newBornPredators = new ArrayList<>();
        ArrayList<Integer> diedPredatorsId = new ArrayList<>();
        ArrayList<Integer> alivePredatorsId = new ArrayList<>();

        for (int k = 0; k < predatorsSize; k++) {
            Animal item = (Animal) predators.get(k);
            if (item != null && !item.isAlive()) {
                continue;
            }
            boolean canMove = true;
            boolean saturation = false;
            do {
                canMove = item.move(this);
                saturation = item.eat();
            }
            while (canMove && !saturation);

            if (!canMove && !saturation) {
                item.die();
                continue;
            }
            if (saturation) {
                alivePredatorsId.add(item.getId());
                item.setCurrMovesByTick(0);
                item.setCurrSaturationAmountByTick(0);
                if (item.getCell().hasSimilarAnimal(item)) {
                    Animal newAnimal = item.reproduce();
                    if (newAnimal != null) {
                        moveByCoords(newAnimal, item.getCell().getCoords());
                        newBornPredators.add(newAnimal);
                    }
                }

            }

        }


        diedPredatorsId = (ArrayList<Integer>) predators.stream().filter(item -> !item.isAlive()).map(item -> item.getId()).collect(Collectors.toList());

//        System.out.println("born:" + newBornPredators.size());
//        System.out.println("died:" + diedPredatorsId.size());
        diedPredatorsId = (ArrayList<Integer>) diedPredatorsId.stream().sorted().collect(Collectors.toList());
        alivePredatorsId = (ArrayList<Integer>) alivePredatorsId.stream().sorted().collect(Collectors.toList());
//        System.out.println(diedPredatorsId);
//        System.out.println(alivePredatorsId);
//        System.out.println("");
        predators.addAll(newBornPredators);

    }

    public void startLiveOneTickHerbivores(ArrayList<SimulationItem> herbivores) {

        int herbivoresSize = herbivores.size();
        ArrayList<SimulationItem> newBornPredators = new ArrayList<>();
        ArrayList<Integer> diedHerbivoresId = new ArrayList<>();
        ArrayList<Integer> aliveHerbivoresId = new ArrayList<>();

        for (int k = 0; k < herbivoresSize; k++) {
            Animal item = (Animal) herbivores.get(k);
            if (item != null && !item.isAlive()) {
                continue;
            }
            boolean canMove = true;
            boolean saturation = false;
            do {
                canMove = item.move(this);
                saturation = item.eat();
            }
            while (canMove && !saturation);

            if (!canMove && !saturation) {
                item.die();
                continue;
            }
            if (saturation) {
                aliveHerbivoresId.add(item.getId());
                item.setCurrMovesByTick(0);
                item.setCurrSaturationAmountByTick(0);
                if (item.getCell().hasSimilarAnimal(item)) {
                    Animal newAnimal = item.reproduce();
                    if (newAnimal != null) {
                        moveByCoords(newAnimal, item.getCell().getCoords());
                        newBornPredators.add(newAnimal);
                    }
                }

            }

        }


        diedHerbivoresId = (ArrayList<Integer>) herbivores.stream().filter(item -> !item.isAlive()).map(item -> item.getId()).collect(Collectors.toList());

//        System.out.println("born:" + newBornPredators.size());
//        System.out.println("died:" + diedHerbivoresId.size());
        diedHerbivoresId = (ArrayList<Integer>) diedHerbivoresId.stream().sorted().collect(Collectors.toList());
        aliveHerbivoresId = (ArrayList<Integer>) aliveHerbivoresId.stream().sorted().collect(Collectors.toList());

//        System.out.println("");
        herbivores.addAll(newBornPredators);

    }

    public void moveByCoords(SimulationItem item, Coords coords) {
        board.moveSimulationItem(item, coords);
    }
}
