package com.dudarev.island;


import com.dudarev.island.classes.base.Animal;
import com.dudarev.island.classes.base.Plant;
import com.dudarev.island.classes.base.SimulationItem;
import com.dudarev.island.classes.board.Board;
import com.dudarev.island.classes.utils.BoardInitializer;
import com.dudarev.island.classes.utils.LiveSimulator;
import com.dudarev.island.classes.utils.MovementManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws InterruptedException {

        LiveSimulator liveSimulator = new LiveSimulator();
        Board board = liveSimulator.getBoard();
        MovementManager movementManager = liveSimulator.getMovementManager();
        BoardInitializer boardInitializer = liveSimulator.getBoardInitializer();

        ArrayList<Animal> predators = new ArrayList<>();
        for (int i = 0; i <= board.getRightBoundX(); i++) {
            for (int j = 0; j <= board.getDownBoundY(); j++) {
                Board.Cell cell = board.getScheme().get(i).get(j);
                predators.addAll(cell.getAllPredators());
            }
        }

        ArrayList<Animal> herbivores = new ArrayList<>();
        for (int i = 0; i <= board.getRightBoundX(); i++) {
            for (int j = 0; j <= board.getDownBoundY(); j++) {
                Board.Cell cell = board.getScheme().get(i).get(j);
                herbivores.addAll(cell.getAllHerbivores());
            }
        }

        ArrayList<Plant> plants = new ArrayList<>();
        for (int i = 0; i <= board.getRightBoundX(); i++) {
            for (int j = 0; j <= board.getDownBoundY(); j++) {
                Board.Cell cell = board.getScheme().get(i).get(j);
                plants.addAll(cell.getAllPlants());
            }
        }

        System.out.println("Start!, herbivores count: " + herbivores.size());
        System.out.println("Start!, predators count: " + predators.size());
        System.out.println("Start!, grass count: " + plants.size());
        int ticks = 0;
        while (predators.size() > 0 && herbivores.size() > 0) {
            plants = liveSimulator.startPlantsGrowOneTick(plants);


            predators = liveSimulator.startAnimalsLiveOneTick(predators);


            herbivores = liveSimulator.startAnimalsLiveOneTick(herbivores);

            System.out.println("-------------------");
            System.out.println("TiCK! herbivores count: " + herbivores.size());
            System.out.println("TiCK! predators count: " + predators.size());
            System.out.println("TiCK! grass count: " + plants.size());
            System.out.println("ticks=" + ticks);
            System.out.println("-------------------");

//            board.printScheme();
            Thread.sleep(100);


            ticks++;
        }

        System.out.println("EnD!, herbivores count: " + herbivores.size());
        System.out.println("EnD!, predators count: " + predators.size());
        System.out.println("EnD!, grass count: " + plants.size());

        System.out.println("tick number=" + ticks);


    }
}
