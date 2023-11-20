package org.example;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class lockObject {
    Set<Integer> criticalAncestors;
    int Id;
    int mode;
    int Seq;

    CountDownLatch waitCondition = new CountDownLatch(1);

    lockObject(int Id, int mode, Set<Integer> criticalAncestors) {
        this.Id = Id;
        this.mode = mode;
        this.Seq = -1;
        this.criticalAncestors = criticalAncestors;
    }
}
