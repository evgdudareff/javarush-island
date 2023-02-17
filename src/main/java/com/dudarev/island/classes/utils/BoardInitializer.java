package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.*;
import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.plants.Grass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardInitializer {
    private final ArrayList<SimulationItem> simulationItems = new ArrayList<>();

    public int getRandomCountOfItemsToCreate(SimulationItem simulationItem, Random random) {
        int maxItemsPerCell = simulationItem.getMaxItemsPerCell();

        if (simulationItem instanceof Grass) {
            return random.nextInt(maxItemsPerCell, maxItemsPerCell * 5);
        }

        if (simulationItem instanceof Predator) {
            return maxItemsPerCell * 500;

        }

        if (simulationItem instanceof Herbivore) {
            return maxItemsPerCell / 10;
        }
        return maxItemsPerCell;
    }

    public void randomlyShuffleItemsOnBoard(MovementManager movementManagerUtils) {
        Random random = new Random();
        Board board = movementManagerUtils.board;

        simulationItems.forEach(item -> {
            Coords coords = new Coords(
                    random.nextInt(0, board.getRightBoundX() + 1),
                    random.nextInt(0, board.getDownBoundY() + 1)
            );

            movementManagerUtils.moveByCoords(item, coords);
        });
    }


    public boolean init(MovementManager movementManagerUtils) {
        SimulationClassesLoader simulationClassesLoader = new SimulationClassesLoader();
        SimulationItemsFactory simulationItemsFactory = new SimulationItemsFactory();
        Random random = new Random();

        ArrayList<SimulationItem> simulationClasses = simulationClassesLoader.getLoadedClasses();
        try {
            for (int i = 0; i < simulationClasses.size(); i++) {
                SimulationItem simulationItem = simulationClasses.get(i);
                int countOfItemsToCreate = getRandomCountOfItemsToCreate(simulationItem, random);

                if (simulationItem instanceof Animal) {
                    Class animalClazz = simulationItem.getClass();
                    ArrayList<Animal> animals = simulationItemsFactory.createAnimalsByType(animalClazz, countOfItemsToCreate);
                    simulationItems.addAll(animals);
                } else if (simulationItem instanceof Plant) {
                    Class plantClazz = simulationItem.getClass();
                    ArrayList<Plant> plants = simulationItemsFactory.createPlantsByType(plantClazz, countOfItemsToCreate);
                    simulationItems.addAll(plants);
                }
            }
        } catch (Exception e) {
            System.out.println("Board init failed\\n" + Arrays.toString(e.getStackTrace()));
            return false;
        }

        randomlyShuffleItemsOnBoard(movementManagerUtils);

        return true;
    }
}
