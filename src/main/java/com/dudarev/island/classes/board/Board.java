package com.dudarev.island.classes.board;

import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.utils.Coords;

import java.util.ArrayList;
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

    public void moveSimulationItem(SimulationItem item, Coords moveToCoords) {
        Cell itemCurrCell = item.getCell();
        itemCurrCell.removeSimulationItem(item);

        Cell targetCell = scheme.get(moveToCoords.getX()).get(moveToCoords.getY());


        targetCell.addSimulationItem(item);
    }

    public static class Cell {
        private ArrayList<SimulationItem> simulationItems = new ArrayList<>();
        private Coords coords;

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
            item.linkToCell(this);
        }

        private void removeSimulationItem(SimulationItem item) {
            if (item == null) {
                return;
            }
            ArrayList<Integer> simulationItemsIds = (ArrayList<Integer>) this.simulationItems.stream()
                    .map(currItem -> currItem.getId())
                    .collect(Collectors.toList());

            int currItemIndex = simulationItemsIds.indexOf(item.getId());

            if (currItemIndex != -1) {
                this.simulationItems.remove(currItemIndex);
                item.linkToCell(null);
            }
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
