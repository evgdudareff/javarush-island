package com.dudarev.island.classes.base;

public abstract class Animal extends SimulationItem {


    abstract public int getCellMovesPerCycle();

    abstract public double getSaturationAmount();

    public void eat(SimulationItem target) {
    }

    ;

}
