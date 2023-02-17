package com.dudarev.island.classes.base;

import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.utils.SimulationItemsFactory;

public abstract class SimulationItem {
    private Board.Cell currentCell;
    private static int globalId = 0;
    private int id;
    public static SimulationItemsFactory factory = new SimulationItemsFactory();

    abstract public String getImage();

    abstract public double getWeight();

    abstract public int getMaxItemsPerCell();

    public int getId() {
        return id;
    }

    public SimulationItem() {
        id = globalId++;
    }

    public Board.Cell getCell() {
        return currentCell;
    }

    public void setCell(Board.Cell cell) {
        currentCell = cell;
    }

    public void die() {
        Board.Cell currCell = this.getCell();
        currCell.removeSimulationItem(this);
    }

    public boolean isAlive() {
        return this.getCell() != null;
    }
}
