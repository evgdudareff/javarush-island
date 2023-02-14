package com.dudarev.island.classes.base;

import java.lang.reflect.Constructor;

public class SimulationItemsFactory {
    public <T extends Animal>T createAnimalByType(Class<T> animalClass) throws Exception {
        Constructor<T> animalConstructor = animalClass.getConstructor();
        return animalConstructor.newInstance();
    }

    public <T extends Plant>T createPlantByType(Class<T> plantClass) throws Exception {
        Constructor<T> animalConstructor = plantClass.getConstructor();
        return animalConstructor.newInstance();
    }
}
