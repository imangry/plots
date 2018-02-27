package com.qunar.concurrent;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Date: 17/12/27
 * User: lvshi
 */
public class waitNotify {
    public static final LinkedList<String> queue = Lists.newLinkedList();
    public static final Random random = new Random();

    public static void main(String[] args) {

        new Producer().start();
        new Consumer().start();
        new Consumer().start();
        new Consumer().start();
    }

    private static void waitNotify() {
        final Object lock = new Object();

        new Thread(() -> {
            System.out.println("A WAITING LOCK");
            synchronized (lock) {
                System.out.println("A GET LOCK");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("A CALL WAIT METHOD");
                    lock.wait();
                    System.out.println("A WAIT END");
                    System.out.println("A RELEASE LOCK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("B WAITING LOCK");
            synchronized (lock) {
                System.out.println("B GET LOCK");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("B CALL NOTIFY");
                    lock.notify();
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("B RELEASE LOCK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static class Producer extends Thread {
        @Override
        public void run() {

            while (true) {
                synchronized (queue) {
                    while (queue.size() == 100) {
                        try {
                            System.out.println("queue is full,queue size is " + queue.size());
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.addLast(String.valueOf(random.nextInt()));
                    queue.notifyAll();
                    System.out.println("producer produce,queue size " + queue.size());
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("queue is empty ");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.removeFirst();
                    queue.notifyAll();
                    System.out.println("consumer consume,queue size " + queue.size());
                }
            }
        }
    }
}
