package com.dudarev.island.classes.base;

import com.dudarev.island.classes.board.Board;

public abstract class SimulationItem {
    private double weight;
    private int maxItemsPerCell;
    private Board.Cell currentCell;
    private static int id = 0;

    public SimulationItem() {
        id++;
    }

    abstract public String getImage();

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
