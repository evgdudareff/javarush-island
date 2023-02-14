package com.dudarev.island.classes.base;

import com.dudarev.island.classes.board.Board;

public abstract class SimulationItem {
    private Board.Cell currentCell;
    private static int globalId = 0;
    private int id = 0;

    public SimulationItem() {
        id = globalId++;
    }

    abstract public String getImage();

    abstract public double getWeight();

    abstract public int getMaxItemsPerCell();

    public int getId() {
        return id;
    }

    public Board.Cell getCell() {
        return currentCell;
    }


    public void linkToCell(Board.Cell cell) {
        currentCell = cell;
    }

    public void reproduction() {
    }
}
