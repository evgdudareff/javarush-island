package com.dudarev.island.classes.predators;

import com.dudarev.island.classes.base.Predator;

public class Fox extends Predator {
    private final String image = "\uD83E\uDD8A";
    private final double weight = 8;
    private final int maxItemsPerCell = 30;
    private final int cellMovesPerCycle = 2;
    private final double saturationAmount = 2;

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
