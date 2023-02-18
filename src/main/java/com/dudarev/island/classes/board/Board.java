package com.dudarev.island.classes.board;

import com.dudarev.island.classes.base.*;
import com.dudarev.island.classes.utils.Coords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Board {
    private final int leftBoundX;
    private final int rightBoundX;
    private final int upBoundY;
    private final int downBoundY;
    private final ArrayList<ArrayList<Board.Cell>> scheme = new ArrayList<>();

    public Board(int width, int height) {
        leftBoundX = 0;
        rightBoundX = width - 1;
        upBoundY = 0;
        downBoundY = height - 1;

        for (int i = leftBoundX; i <= rightBoundX; i++) {
            scheme.add(new ArrayList<>());
            for (int j = upBoundY; j <= downBoundY; j++) {
                scheme.get(i).add(new Cell(i, j));
            }
        }

    }

    public void printScheme() {
        scheme.forEach(System.out::println);
    }

    public int getLeftBoundX() {
        return leftBoundX;
    }

    public int getRightBoundX() {
        return rightBoundX;
    }

    public int getDownBoundY() {
        return downBoundY;
    }

    public int getUpBoundY() {
        return upBoundY;
    }

    public ArrayList<ArrayList<Board.Cell>> getScheme() {
        return scheme;
    }

    public void moveSimulationItem(SimulationItem item, Coords moveToCoords) {
        Cell itemCurrCell = item.getCell();
        if (itemCurrCell != null) {
            itemCurrCell.removeSimulationItem(item);

        }

        Cell targetCell = scheme.get(moveToCoords.getX()).get(moveToCoords.getY());


        targetCell.addSimulationItem(item);
    }

    public HashMap<String, ArrayList<? extends SimulationItem>> getAllAliveItemsMap() {
        ArrayList<Animal> alivePredators = new ArrayList<>();
        ArrayList<Animal> aliveHerbivores = new ArrayList<>();
        ArrayList<Plant> alivePlants = new ArrayList<>();
        HashMap<String, ArrayList<? extends SimulationItem>> map = new HashMap<>() {{
            put("predators", alivePredators);
            put("herbivores", aliveHerbivores);
            put("plants", alivePlants);
        }};

        for (int i = 0; i <= getRightBoundX(); i++) {
            for (int j = 0; j <= getDownBoundY(); j++) {
                Board.Cell cell = getScheme().get(i).get(j);
                alivePredators.addAll(cell.getAllAlivePredators());
                aliveHerbivores.addAll(cell.getAllAliveHerbivores());
                alivePlants.addAll(cell.getAllAlivePlants());
            }
        }
        return map;
    }


    public Cell getCellByCoords(Coords coords) {
        return scheme.get(coords.getX()).get(coords.getY());
    }

    public static class Cell {
        private final ArrayList<SimulationItem> simulationItems = new ArrayList<>();
        private final Coords coords;

        public Cell(int x, int y) {
            coords = new Coords(x, y);
        }

        public Coords getCoords() {
            return coords;
        }


        public ArrayList<SimulationItem> getCurrentSimulationItems() {
            return simulationItems;
        }

        private void addSimulationItem(SimulationItem item) {
            simulationItems.add(item);
            item.setCell(this);
        }

        public void removeSimulationItem(SimulationItem item) {
            ArrayList<Integer> simulationItemsIds = (ArrayList<Integer>) this.simulationItems.stream()
                    .map(SimulationItem::getId)
                    .collect(Collectors.toList());

            int currItemIndex = simulationItemsIds.indexOf(item.getId());

            if (currItemIndex != -1) {
                this.simulationItems.remove(currItemIndex);
                item.setCell(null);
            }
        }

        public ArrayList<Animal> getAllPredators() {
            ArrayList<Animal> predators = new ArrayList<>();
            for (var item : this.getCurrentSimulationItems()) {
                if (item instanceof Predator) {
                    predators.add((Animal) item);
                }
            }
            return predators;
        }

        public ArrayList<Animal> getAllAlivePredators() {
            ArrayList<Animal> predators = getAllPredators();
            return (ArrayList<Animal>) predators
                    .stream()
                    .filter(Animal::isAlive)
                    .collect(Collectors.toList());
        }


        public ArrayList<Animal> getAllHerbivores() {
            ArrayList<Animal> herbivores = new ArrayList<>();
            for (var item : this.getCurrentSimulationItems()) {
                if (item instanceof Herbivore) {
                    herbivores.add((Animal) item);
                }
            }
            return herbivores;
        }

        public ArrayList<Animal> getAllAliveHerbivores() {
            ArrayList<Animal> herbivores = getAllHerbivores();
            return (ArrayList<Animal>) herbivores
                    .stream()
                    .filter(Animal::isAlive)
                    .collect(Collectors.toList());
        }

        public ArrayList<Plant> getAllPlants() {
            ArrayList<Plant> plants = new ArrayList<>();
            for (var item : this.getCurrentSimulationItems()) {
                if (item instanceof Plant) {
                    plants.add((Plant) item);
                }
            }
            return plants;
        }

        public ArrayList<Plant> getAllAlivePlants() {
            ArrayList<Plant> plants = getAllPlants();
            return (ArrayList<Plant>) plants
                    .stream()
                    .filter(Plant::isAlive)
                    .collect(Collectors.toList());
        }

        public boolean hasSimilarAnimal(Animal animal) {
            int similarAnimalsCount = getSimilarAnimalCount(animal);
            return similarAnimalsCount > 0;
        }

        public int getSimilarAnimalCount(Animal animal) {
            Board.Cell currCell = animal.getCell();
            List<SimulationItem> similarAnimals = currCell.getCurrentSimulationItems()
                    .stream()
                    .filter(item -> item.getId() != animal.getId() && item.getClass().isInstance(animal))
                    .collect(Collectors.toList());
            return similarAnimals.size();
        }


        @Override
        public String toString() {
            ArrayList<String> simulationItems = (ArrayList<String>) this.simulationItems.stream()
                    .map(item -> item.getImage() + "-id-" + item.getId())
                    .collect(Collectors.toList());

            return "Cell(" + coords.getX() + "," + coords.getY() + "),items:" + simulationItems;
        }
    }
}
