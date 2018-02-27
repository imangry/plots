package com.qunar;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * Date: 17/12/22
 * User: lvshi
 */
public class SkipListConsistent<T> {

    private ConcurrentSkipListMap<Integer, T> skipListMap;

    public SkipListConsistent() {
        skipListMap = new ConcurrentSkipListMap<>();
    }


    public void addNode(T obj, int weight) {
        for (int i = 0; i < weight; i++) {
            skipListMap.put(Hashing.murmur3_32()
                    .hashString(obj.toString() + i, Charsets.UTF_8)
                    .asInt(), obj);
        }
    }

    public void delNode(T obj, int weight) {
        for (int i = 0; i < weight; i++) {
            skipListMap.remove(Hashing.murmur3_32()
                    .hashString(obj.toString() + i, Charsets.UTF_8)
                    .asInt());
        }
    }

    public T get(T key) {
        int hashKey = Hashing.murmur3_32().hashString(key.toString(), Charsets.UTF_8).asInt();
        Map.Entry<Integer, T> entry = skipListMap.ceilingEntry(hashKey);
        if (entry == null) {
            entry = skipListMap.firstEntry();
        }
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    public static void main(String[] args) throws InterruptedException {

        SkipListConsistent<String> consistent = new SkipListConsistent<>();

        consistent.addNode("www.google.com", 1000);
        consistent.addNode("www.baidu.com", 1000);
        consistent.addNode("www.sogo.com", 1000);

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            testSpeed(consistent, "www.google.com", 1000, true);
            latch.countDown();
        }).start();
        new Thread(() -> {
            testSpeed(consistent, "www.baidu.com", 1000, false);
            latch.countDown();
        }).start();
        latch.await();
    }

    private static void testSpeed(SkipListConsistent<String> consistent, String node, int weight, boolean del) {
        long start = System.currentTimeMillis();
        Map<String, Integer> result = Maps.newHashMap();

        for (int i = 0; i < 100000; i++) {
            String v = consistent.get(RandomStringUtils.random(12));
            Integer count = result.get(v);
            if (count != null) {
                result.put(v, count + 1);
            } else
                result.put(v, 0);
            if (i % 300 == 0) {
                if (del)
                    consistent.delNode(node, weight);
                else
                    consistent.addNode(node, weight);
            }
        }

        System.out.println(Joiner.on(" ; ").withKeyValueSeparator(" = ").join(result));
        System.out.println(System.currentTimeMillis() - start);
    }

}
