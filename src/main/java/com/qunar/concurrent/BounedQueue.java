package com.qunar.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BounedQueue {

    private Object[] objects;

    private int count;
    private int addIndex;
    private int removeIndex;


    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();


    public BounedQueue(int size) {
        this.objects = new Object[size];
    }

    public void add(Object object) throws InterruptedException {
        lock.lock();
        try{
            while (count == objects.length){
                notFull.await();
            }
            objects[addIndex++] = object;
            if (addIndex == objects.length) {
                addIndex = 0;
            }
            count++;
            notEmpty.signal();

        }finally {
            lock.unlock();
        }
    }

    public Object remove() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0) {
                notEmpty.await();
            }
            Object resp = objects[removeIndex++];
            if (removeIndex == objects.length) {
                removeIndex = 0;
            }
            count--;
            notFull.signal();
            return resp;
        }finally {
            lock.unlock();
        }
    }

}
