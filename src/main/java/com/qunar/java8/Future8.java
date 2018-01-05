package com.qunar.java8;

import java.util.concurrent.CompletableFuture;

/**
 * Date: 17/12/25
 * User: lvshi
 */
public class Future8 {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        int processors = Runtime.getRuntime().availableProcessors();

    }
}
