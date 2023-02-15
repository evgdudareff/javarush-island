package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.herbivores.*;
import com.dudarev.island.classes.predators.Wolf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChanceToBeEaten {
    private final HashMap<String, HashMap<String, Integer>> chanceToBeEatenMap = new HashMap<>() {{
        put(Wolf.class.getName(), new HashMap<>() {{
            put(Horse.class.getName(), 10);
            put(Deer.class.getName(), 15);
            put(Rabbit.class.getName(), 60);
            put(Mouse.class.getName(), 80);
            put(Goat.class.getName(), 60);
            put(Sheep.class.getName(), 70);
            put(Boar.class.getName(), 15);
            put(Buffalo.class.getName(), 10);
            put(Rabbit.class.getName(), 60);
            put(Duck.class.getName(), 40);
        }});
    }};

    public boolean isAttackerEatsVictim(Animal attacker, SimulationItem victim) {
        String attackerClassName = attacker.getClass().getName();
        if (!chanceToBeEatenMap.containsKey(attackerClassName)) {
            return false;
        }

        HashMap<String, Integer> attackersPossibleVictimsMap = chanceToBeEatenMap.get(attackerClassName);
        String victimClassName = victim.getClass().getName();
        if (!attackersPossibleVictimsMap.containsKey(victimClassName)) {
            return false;
        }

        int randomChanceToEatVictim = new Random().nextInt(0, 100);
        int configurationChanceToEatVictim = attackersPossibleVictimsMap.get(victimClassName);

        return randomChanceToEatVictim <= configurationChanceToEatVictim;
    }

    public ArrayList<String> getVictimClassNamesByAttackerName(String attackerClassName) {
        ArrayList<String> victims = new ArrayList<>();
        if (!chanceToBeEatenMap.containsKey(attackerClassName)) {
            return victims;
        }
        victims.addAll(chanceToBeEatenMap.get(attackerClassName).keySet());
        return victims;
    }
}
