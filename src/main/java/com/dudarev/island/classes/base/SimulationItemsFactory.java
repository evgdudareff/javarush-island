package com.dudarev.island.classes.base;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class SimulationItemsFactory {
    public <T extends Animal> T createAnimalByType(Class<T> animalClass) throws Exception {
        Constructor<T> animalConstructor = animalClass.getConstructor();
        return animalConstructor.newInstance();
    }

    public <T extends Animal> ArrayList<T> createAnimalsByType(Class<T> animalClass, int count) throws Exception {
        ArrayList<T> animals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            animals.add(createAnimalByType(animalClass));
        }
        return animals;
    }

    public <T extends Plant> T createPlantByType(Class<T> plantClass) throws Exception {
        Constructor<T> plantConstructor = plantClass.getConstructor();
        return plantConstructor.newInstance();
    }

    public <T extends Plant> ArrayList<T> createPlantsByType(Class<T> plantClass, int count) throws Exception {
        ArrayList<T> plants = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            plants.add(createPlantByType(plantClass));
        }
        return plants;
    }
}
