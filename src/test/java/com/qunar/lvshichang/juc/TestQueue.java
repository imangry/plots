package com.qunar.lvshichang.juc;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;

public class TestQueue {


    @Test
    public void testQueueBenchmark(){

    }


    static class Producer implements Runnable{

        private BlockingQueue<Integer> queue;
        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0 ;i < 100000;i++) {
                queue.offer(i);
            }
            System.out.println("producer_" + Thread.currentThread().getName() + " cost " + (System.currentTimeMillis() - start));
        }
    }

}
