package com.dudarev.island.classes.tests;

import com.dudarev.island.classes.herbivores.Horse;
import com.dudarev.island.classes.herbivores.Mouse;
import com.dudarev.island.classes.predators.Bear;
import com.dudarev.island.classes.predators.Wolf;
import com.dudarev.island.classes.utils.ChanceToBeEaten;

import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RED;
import static com.dudarev.island.classes.tests.TestsCostants.ANSI_RESET;

public class ChanceToBeEatenTests {
    public  void checkThatWolfEatsHorseAtLeastOneTime() throws Exception {
        System.out.println("checkThatWolfEatsHorseAtLeastOneTime starts 1000 iterations");

        int success = 0;
        int failure = 0;

        for (int i = 0; i < 1000; i++) {
            if (ChanceToBeEaten.isAttackerEatsVictim(new Wolf(), new Horse())) {
                success++;
            } else {
                failure++;
            }

        }

        if (success == 0) {
            throw new Exception("checkThatWolfEatsHorseAtLeastOneTime");
        }

        System.out.println("Wolf could eat Horse:" + success);
        System.out.println("Wolf could not eat Horse:" + failure);

        System.out.println("checkThatWolfEatsHorseAtLeastOneTime ends");
        System.out.println("-----------------------------------------");
    }

    public void checkThatWolfEatsMouseAtLeastOneTime() throws Exception {
        System.out.println("checkThatWolfEatsMouseAtLeastOneTime starts 1000 iterations");

        int success = 0;
        int failure = 0;

        for (int i = 0; i < 1000; i++) {
            if (ChanceToBeEaten.isAttackerEatsVictim(new Wolf(), new Mouse())) {
                success++;
            } else {
                failure++;
            }

        }

        if (success == 0) {
            throw new Exception("checkThatWolfEatsMouseAtLeastOneTime");
        }

        System.out.println("Wolf could eat Mouse:" + success);
        System.out.println("Wolf could not eat Mouse:" + failure);

        System.out.println("checkThatWolfEatsMouseAtLeastOneTime ends");
        System.out.println("-----------------------------------------");
    }

    public void checkThatWolfNeverEatsHimSelf() throws Exception {
        System.out.println("checkThatWolfNeverEatsHimSelf starts 1000 iterations");

        int success = 0;
        int failure = 0;

        for (int i = 0; i < 1000; i++) {
            if (ChanceToBeEaten.isAttackerEatsVictim(new Wolf(), new Wolf())) {
                success++;
            } else {
                failure++;
            }

        }
        if (success > 0) {
            throw new Exception("checkThatWolfNeverEatsHimSelf");
        }

        System.out.println("Wolf could eat himself:" + success);
        System.out.println("Wolf could not eat himself:" + failure);

        System.out.println("checkThatWolfNeverEatsHimSelf ends");
        System.out.println("-----------------------------------------");
    }

    public void checkThatWolfNeverEatsBear() throws Exception {
        System.out.println("checkThatWolfNeverEatsBear starts 1000 iterations");

        int success = 0;
        int failure = 0;

        for (int i = 0; i < 1000; i++) {
            if (ChanceToBeEaten.isAttackerEatsVictim(new Wolf(), new Bear())) {
                success++;
            } else {
                failure++;
            }

        }

        if (success > 0) {
            throw new Exception("checkThatWolfNeverEatsBear");
        }

        System.out.println("Wolf could eat Bear:" + success);
        System.out.println("Wolf could not eat Bear:" + failure);

        System.out.println("checkThatWolfNeverEatsBear ends");
        System.out.println("-----------------------------------------");
    }

    public void runTests() {
        try {
            checkThatWolfEatsMouseAtLeastOneTime();
            checkThatWolfEatsHorseAtLeastOneTime();
            checkThatWolfNeverEatsHimSelf();
            checkThatWolfNeverEatsBear();
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Test " + e.getMessage() + " failed" + ANSI_RESET);
        }

    }
}
