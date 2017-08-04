package com.qunar.lvshichang.guava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {

    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            .weakValues()
            .recordStats()
            .build();
    @Test
    public void guavaCache() throws ExecutionException, InterruptedException {

        cache.put("aaa", "1111");

        TimeUnit.SECONDS.sleep(3);

        System.out.println(cache.get("aaa", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }));
    }

    public static String fetchFromDB() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
        return String.valueOf(new Random().nextInt());
    }
}
