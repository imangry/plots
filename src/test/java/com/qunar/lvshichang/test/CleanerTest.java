package com.qunar.lvshichang.test;

import sun.misc.Cleaner;

import java.util.concurrent.TimeUnit;

public class CleanerTest {

    /**
     * 虚引用 在指向的对象的引用（除了虚引用）都失效，将要被回收时，会被放入关联的引用队列
     */
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Cleaner clean = Cleaner.create(obj, new Runnable() {
            @Override
            public void run() {
                System.out.println("obj 已经被回收");

            }
        });
        System.out.println("gc 前");
        obj = null;
        Runtime.getRuntime().gc();
        TimeUnit.SECONDS.sleep(5);

    }
}
