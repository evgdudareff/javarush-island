package com.dudarev.island.classes.tests;

import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.base.SimulationItemsFactory;
import com.dudarev.island.classes.plants.Grass;
import com.dudarev.island.classes.predators.Bear;
import com.dudarev.island.classes.predators.Wolf;

import java.util.ArrayList;

public class SimulationItemsFactoryTests {

    private void checkAnimalsAndPlantsCreated() {
        SimulationItemsFactory simulationItemsFactory = new SimulationItemsFactory();
        try {
            ArrayList<SimulationItem> simulationItems = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                simulationItems.add(simulationItemsFactory.createAnimalByType(Wolf.class));
                simulationItems.add(simulationItemsFactory.createPlantByType(Grass.class));
                simulationItems.add(simulationItemsFactory.createAnimalByType(Bear.class));
            }

            simulationItems.forEach(item -> System.out.println(item.getId() + item.getImage()));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void runTests() {
        checkAnimalsAndPlantsCreated();
    }

}
