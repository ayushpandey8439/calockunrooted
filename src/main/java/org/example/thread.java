package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class thread extends Thread{
    Set<Integer> anc = new HashSet<>(Arrays.asList(new Integer[]{1}));

    lockObject o = new lockObject((int) getId()%2, 1, anc);
    public void run(){
        try {
            lockPool.lock(o, (int) getId()%2);
            System.out.println("Thread " + getId()%2 + " is running");
            thread.sleep(10000);
            lockPool.unlock((int) getId()%2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
