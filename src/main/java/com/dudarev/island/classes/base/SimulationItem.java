package com.dudarev.island.classes.base;

import com.dudarev.island.classes.utils.Coords;

public abstract class SimulationItem {
    private double weight;
    private int maxItemsPerCell;
    private Coords coords;

    public void moveTo(int x, int y) {

    }

    public void reproduction() {
    }

    public void setCoords(int x, int y) {
        this.coords = new Coords(x, y);
    }

    public Coords getCoords() {
        return this.coords;
    }
}
