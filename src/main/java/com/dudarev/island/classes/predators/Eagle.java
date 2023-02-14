package com.dudarev.island.classes.predators;

import com.dudarev.island.classes.base.Predator;

public class Eagle extends Predator {
    private final String image = "\uD83E\uDD85";
    private final double weight = 6;
    private final int maxItemsPerCell = 20;
    private final int cellMovesPerCycle = 3;
    private final double saturationAmount = 1;

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }

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
