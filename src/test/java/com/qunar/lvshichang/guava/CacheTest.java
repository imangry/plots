package com.qunar.lvshichang.guava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {


    @Test
    public void guavaCache() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .recordStats()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<String, String>() {
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification.getKey() + " " + notification.getValue());
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return fetchFromDB();
                    }
                });
        cache.put("aaa", "1111");
        cache.put("bbb", "1111");
        cache.put("ccc", "1111");
        cache.put("ddd", "1111");
        cache.put("eee", "1111");
        cache.put("fff", "1111");
        TimeUnit.SECONDS.sleep(8);

        cache.get("aaa");
        cache.get("bbb");
        cache.get("aaa");
        cache.get("ccc");
        cache.get("ddd");
    }

    public static String fetchFromDB() {
        return String.valueOf(new Random().nextInt());
    }
}
