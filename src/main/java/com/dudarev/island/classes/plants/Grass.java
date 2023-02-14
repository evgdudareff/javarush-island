package com.dudarev.island.classes.plants;

import com.dudarev.island.classes.base.Plant;

public class Grass extends Plant {
    private final String image = "\uD83C\uDF31";
    private final double weight = 1;
    private final int maxItemsPerCell = 200;

    public String getImage() {
        return image;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }
}
