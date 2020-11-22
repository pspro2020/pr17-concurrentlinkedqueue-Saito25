package main;

import garbage.ConveyerBelt;
import garbage.GarbageSorter;
import garbage.Hopper;

import java.util.concurrent.TimeUnit;

public class GarbageLogistic {

    private final int TRASH_FOR_HOPPER = 5;
    private final ConveyerBelt conveyerBelt = new ConveyerBelt();
    private final Thread[] hopperThreads = new Thread[5];
    private final Thread garbageSorterThread = new Thread(new GarbageSorter(conveyerBelt), "ConveterBelt");

    public GarbageLogistic() throws InterruptedException {
        initHopperThreads();
        initializingGarbageSorter();
        initGarbageSorter();
        waitForGarbageSorter();
    }

    private void initHopperThreads() {
        for (int i = 0; i < hopperThreads.length; i++) {
            hopperThreads[i] = new Thread(new Hopper(Integer.toString(i+1), conveyerBelt, TRASH_FOR_HOPPER));
        }

        for (int i = 0; i < hopperThreads.length; i++) {
            hopperThreads[i].start();
        }
    }

    private void initializingGarbageSorter() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

    private void initGarbageSorter() {
        garbageSorterThread.start();
    }

    private void waitForGarbageSorter() throws InterruptedException {
        garbageSorterThread.join();
    }
}
