package com.qunar.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Date: 18/01/05
 * User: lvshi
 */

public class CasAtomic {
    static class CASInteger {
        private volatile int value;
        private static final AtomicIntegerFieldUpdater<CASInteger> updater = AtomicIntegerFieldUpdater.newUpdater(CASInteger.class, "value");

        public CASInteger(int i) {
            this.value = i;
        }

        //CAS返回true则更新成功，否则拿到最新的值 再次尝试去更新
        public final int getAndIncrement() {
            int v;
            do {
                v = value;
            } while (!updater.compareAndSet(this, v, v + 1));
            return v;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(5);
        CASInteger cas = new CASInteger(0);
        final Integer[] unsafe = {0};
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    cas.getAndIncrement();
                    unsafe[0]++;
                }
                countDown.countDown();
            }).start();
        }
        countDown.await();
        System.out.println(cas.getValue());
        System.out.println(unsafe[0]);

    }
}
