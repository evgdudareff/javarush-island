package com.dudarev.island.classes.utils;

import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.SimulationItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EatingManager {
    private final HashMap<String, HashMap<String, Integer>> chanceToBeEatenMap = ChanceToBeEatenConfig.getConfig();

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

        int randomChanceToEatVictim = new Random().nextInt(0, 101);
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
