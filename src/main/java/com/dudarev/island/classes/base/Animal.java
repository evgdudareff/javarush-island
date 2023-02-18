package com.dudarev.island.classes.base;

import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.utils.EatingManager;
import com.dudarev.island.classes.utils.MovementManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Animal extends SimulationItem {

    private int currMovesByTick = 0;
    private double currSaturationAmountByTick = 0;

    abstract public int getHowMuchTickCouldLiveWithoutSaturation();

    abstract public void decrementHowMuchTickCouldLiveWithoutSaturation();

    abstract public int getCellMovesPerCycle();

    abstract public double getSaturationAmount();

    public int getCurrMovesByTick() {
        return currMovesByTick;
    }

    public void setCurrMovesByTick(int count) {
        currMovesByTick = count;
    }

    public void incrementCurrMovesByTick() {
        currMovesByTick++;
    }

    public double getCurrSaturationAmountByTick() {
        return currSaturationAmountByTick;
    }

    public void setCurrSaturationAmountByTick(double amount) {
        currSaturationAmountByTick = amount;
    }

    public boolean eat() {
        boolean saturation = false;
        EatingManager eatingManager = new EatingManager();
        Board.Cell currCell = this.getCell();
        ArrayList<SimulationItem> allItemsOnCell = currCell.getCurrentSimulationItems();
        ArrayList<String> possibleVictimsClassNames = eatingManager.getVictimClassNamesByAttackerName(this.getClass().getName());

        ArrayList<SimulationItem> victims = (ArrayList<SimulationItem>) allItemsOnCell
                .stream()
                .filter(item -> possibleVictimsClassNames.contains(item.getClass().getName()))
                .collect(Collectors.toList());


        for (int i = 0; i < victims.size(); i++) {
            SimulationItem victim = victims.get(i);
            if (!victim.isAlive()) {
                continue;
            }
            double victimWeight = victim.getWeight();
            double currSaturationAmount = this.getCurrSaturationAmountByTick();
            if (currSaturationAmount != 0 && currSaturationAmount >= this.getSaturationAmount()) {
                return true;
            }

            if (eatingManager.isAttackerEatsVictim(this, victim)) {
                this.setCurrSaturationAmountByTick(currSaturationAmount + victimWeight);
                victim.die();
            }
        }


        return saturation;
    }

    public boolean move(MovementManager movementManager) {
        boolean canMove = this.getCurrMovesByTick() < getCellMovesPerCycle();
        if (!canMove) {
            return false;
        }

        movementManager.move(this);
        this.incrementCurrMovesByTick();
        return true;
    }


    public Animal reproduce() {
        try {
            return factory.createAnimalByType(this.getClass());
        } catch (Exception ignored) {
        }
        return null;

    }

    public void resetMovesAndSaturation() {
        setCurrMovesByTick(0);
        setCurrSaturationAmountByTick(0);
    }
}
