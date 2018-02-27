package com.qunar;

import java.util.concurrent.TimeUnit;

/**
 * Date: 18/01/31
 * User: lvshi
 */
public class SleepCpu {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            TimeUnit.MICROSECONDS.sleep(100);
            if (i++ > 10000000) {
                break;
            }
        }
        System.out.println(i);
    }
}
