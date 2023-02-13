package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.herbivores.*;
import com.dudarev.island.classes.predators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardInitializer {
    private ArrayList<SimulationItem> simulationItemsToCreate = new ArrayList<>(
            Arrays.asList(new Wolf(), new Bear(), new Eagle(), new Fox(), new Snake(), new Boar(), new Buffalo(),
                    new Caterpillar(), new Deer(), new Duck(), new Goat(), new Horse(), new Mouse(), new Rabbit(),
                    new Sheep())
    );

    public void initBoard(Movement movementUtils) {
        Board board = movementUtils.board;

        Random random = new Random();
        simulationItemsToCreate.forEach(item -> {
            Coords coords = new Coords(
                    random.nextInt(0, board.getRightBoundX()),
                    random.nextInt(0, board.getDownBoundY())
            );

            movementUtils.moveByCoords(item, coords);
        });
    }
}
