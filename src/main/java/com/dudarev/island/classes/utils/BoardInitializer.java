package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.Plant;
import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.base.SimulationItemsFactory;
import com.dudarev.island.classes.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardInitializer {
    private ArrayList<SimulationItem> simulationItems = new ArrayList<>();

    private int getRandomCountOfItemsToCreate(SimulationItem simulationItem, Random random) {
        int maxItemsPerCell = simulationItem.getMaxItemsPerCell();
        int maxBoundItemsToCreate;

        if (maxItemsPerCell < 10) {
            maxBoundItemsToCreate = maxItemsPerCell;
        } else if (maxItemsPerCell >= 10 && maxItemsPerCell < 50) {
            maxBoundItemsToCreate = maxItemsPerCell / 5;
        } else {
            maxBoundItemsToCreate = maxItemsPerCell / 10;
        }

        return random.nextInt(1, maxBoundItemsToCreate);
    }

    private void randomlyShuffleItemsOnBoard(MovementManager movementManagerUtils, Random random) {
        Board board = movementManagerUtils.board;

        simulationItems.forEach(item -> {
            Coords coords = new Coords(
                    random.nextInt(0, board.getRightBoundX() + 1),
                    random.nextInt(0, board.getDownBoundY() + 1)
            );

            movementManagerUtils.moveByCoords(item, coords);
        });
    }


    public boolean initBoard(MovementManager movementManagerUtils) {
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

        randomlyShuffleItemsOnBoard(movementManagerUtils, random);

        return true;
    }
}
