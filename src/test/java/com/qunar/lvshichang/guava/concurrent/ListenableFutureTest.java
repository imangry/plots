package com.qunar.lvshichang.guava.concurrent;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;

/**
 * Date: 18/02/26
 * User: lvshi
 */
public class ListenableFutureTest {

    @Test
    public void testFuture() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<Object> future = service.submit(
                () -> null);
        Futures.addCallback(future, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object result) {
            }

            @Override
            public void onFailure(Throwable t) {
            }
        }, service);
    }
    @Test
    public void testFuture1() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<Object> future = service.submit(
                () -> 1);
    }
}
