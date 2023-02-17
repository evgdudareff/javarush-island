package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.Plant;
import com.dudarev.island.classes.board.Board;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class LiveSimulator {
    private final Board board = new Board(100, 20);
    private final BoardInitializer boardInitializer = new BoardInitializer();
    MovementManager movementManager = new MovementManager();
    private boolean ready;

    public LiveSimulator() {
        movementManager.linkWithBoard(board);

        try {
            boardInitializer.init(movementManager);
            ready = true;
            System.out.println("Board inited and ready for simulation");
        } catch (Exception e) {
            System.out.println("Board init failed");
        }
    }

    public boolean isReady() {
        return ready;
    }

    public Board getBoard() {
        return board;
    }

    public MovementManager getMovementManager() {
        return movementManager;
    }

    public BoardInitializer getBoardInitializer() {
        return boardInitializer;
    }

    public ArrayList<Animal> startAnimalsLiveOneTick(ArrayList<Animal> initialAnimals) {
        ArrayList<Animal> newlyBornAnimals = new ArrayList<>();

        for (Animal animal : initialAnimals) {
            boolean canMove = true;
            boolean saturationReahed = false;

            if (animal != null && !animal.isAlive()) {
                continue;
            }

            do {
                if (animal != null) {
                    canMove = animal.move(movementManager);
                    saturationReahed = animal.eat();
                }
            }
            while (canMove && !saturationReahed);

            if (!canMove && !saturationReahed) {
                animal.die();
                continue;
            }

            if (saturationReahed) {
                animal.resetMovesAndSaturation();

                if (animal.getCell().hasSimilarAnimal(animal)) {
                    Animal newAnimal = animal.reproduce();
                    if (newAnimal != null) {
                        newlyBornAnimals.add(animal);
                        movementManager.moveByCoords(newAnimal, animal.getCell().getCoords());
                    }
                }

            }

        }

        ArrayList<Animal> survivedAnimals = (ArrayList<Animal>) initialAnimals
                .stream()
                .filter(Animal::isAlive)
                .collect(Collectors.toList());

        survivedAnimals.addAll(newlyBornAnimals);

        return survivedAnimals;
    }

    public ArrayList<Plant> startPlantsGrowOneTick(ArrayList<Plant> initialPlants) {
        ArrayList<Plant> newlyGrowPlants = new ArrayList<>();
        Plant plant = initialPlants.stream().findFirst().get();
        Class plantClazz = plant.getClass();
        Random random = new Random();

        int countOfItemsToCreate = boardInitializer.getRandomCountOfItemsToCreate(plant, random);
        try {
            newlyGrowPlants = plant.factory.createPlantsByType(plantClazz, countOfItemsToCreate);
        } catch (Exception e) {
            System.out.println("Could not grow plants");
        }

        boardInitializer.randomlyShuffleItemsOnBoard(movementManager);

        ArrayList<Plant> survivedPlants = (ArrayList<Plant>) initialPlants
                .stream()
                .filter(Plant::isAlive)
                .collect(Collectors.toList());

        survivedPlants.addAll(newlyGrowPlants);
        return survivedPlants;


    }
}
