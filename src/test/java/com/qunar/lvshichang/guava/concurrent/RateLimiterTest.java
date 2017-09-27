package com.qunar.lvshichang.guava.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RateLimiterTest {

    private static RateLimiter rateLimiter = RateLimiter.create(50);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws InterruptedException {


        log.info("rate limiter: rate {}", rateLimiter.getRate());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Stopwatch started = Stopwatch.createStarted();

                for (int i = 0; i < 20; i++) {
                    log.info("{} this is no {} second,now i :{}", Thread.currentThread(), started.elapsed(TimeUnit.SECONDS), i);
                    double acquire = rateLimiter.acquire(10);
                    log.info("{} acquire:{} seconds",Thread.currentThread(), acquire);
                }
            }
        };
        executorService.submit(runnable);
//        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(100);

    }


}
