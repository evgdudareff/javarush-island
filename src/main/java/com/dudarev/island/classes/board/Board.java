package com.dudarev.island.classes.board;

import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.utils.Coords;

import java.util.ArrayList;

public class Board {
    private int leftBoundX;
    private int rightBoundX;
    private int upBoundY;
    private int downBoundY;
    private ArrayList<ArrayList<Board.Cell>> scheme = new ArrayList<>();

    public Board(int width, int height) {
        leftBoundX = 0;
        rightBoundX = width - 1;
        upBoundY = 0;
        downBoundY = height - 1;

        for (int i = leftBoundX; i < rightBoundX; i++) {
            scheme.add(new ArrayList<>());
            for (int j = upBoundY; j < downBoundY; j++) {
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

    static class Cell {
        private ArrayList<SimulationItem> currentSimulationItems = new ArrayList<>();
        private Coords coords;

        public Cell(int x, int y) {
            coords = new Coords(x, y);
        }

        public void setCurrentSimulationItems(ArrayList<SimulationItem> currentSimulationItems) {
            this.currentSimulationItems = currentSimulationItems;
        }

        public ArrayList<SimulationItem> getCurrentSimulationItems() {
            return currentSimulationItems;
        }

        @Override
        public String toString() {
            return "Cell(" + coords.getX() + "," + coords.getY() + ")";
        }
    }
}
