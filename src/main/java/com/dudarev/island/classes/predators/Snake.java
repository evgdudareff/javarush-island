package com.dudarev.island.classes.predators;

import com.dudarev.island.classes.base.Predator;

public class Snake extends Predator {
    private final String image = "\uD83D\uDC0D";
    private final double weight = 15;
    private final int maxItemsPerCell = 30;
    private final int cellMovesPerCycle = 1;
    private final double saturationAmount = 3;

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public int getCellMovesPerCycle() {
        return cellMovesPerCycle;
    }

    @Override
    public double getSaturationAmount() {
        return saturationAmount;
    }
}
