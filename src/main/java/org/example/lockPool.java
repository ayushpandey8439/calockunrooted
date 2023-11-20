package org.example;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class lockPool {
    static lockObject lockPool[] = new lockObject[100];
    static Integer GlobalSeq = 0;
    static Object mutex = new Object();

    public static void lock(lockObject obj, int threadId) throws InterruptedException {
        synchronized (mutex){
            obj.Seq = ++GlobalSeq;
            lockPool[threadId] = obj;
            System.out.println("Thread " + threadId + " added its request to the lock pool with seq number "+ obj.Seq);
        }

        for(int i = 0; i < lockPool.length; i++){
            if(lockPool[i] == null || lockPool[i].waitCondition.getCount()==0){
                continue;
            }
            lockObject l = lockPool[i];
            if(
                (obj.mode ==1 || (obj.mode==0 && l.mode==1 )) &&
                (lockPool[i].Id == obj.Id || l.criticalAncestors.contains(obj.Id) || obj.criticalAncestors.contains(l.Id)) &&
                l.Seq < obj.Seq
            ){
                System.out.println("Thread " + threadId + " is waiting for thread " + i);
                l.waitCondition.await();
            }
        }
    }

    public static void unlock(int threadId){
        lockPool[threadId].waitCondition.countDown();
//        lockPool[threadId] = null;
    }

}
