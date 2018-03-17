package com.qunar;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * 一致性hash算法类
 *
 * @author litianmei Jul 25, 2014 10:34:30 AM
 */
public class ConsistentHash<T> {

    private final HashFunction hashFunction;                      // 所用的hash函数
    private final int numberOfReplicas;                  // server虚拟节点倍数(100左右比较合理)
    private final SortedMap<Integer, T> circle = new TreeMap<>(); // server节点分布圆
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 初始化一致性hash算法
     *
     * @param hashFunction
     * @param numberOfReplicas
     * @param nodes            server节点集合
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 加入server节点
     *
     * @param node
     */
    public void add(T node) {
        lock.writeLock().lock();
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                circle.put(hashFunction.hashString(node.toString() + i, Charsets.UTF_8).asInt(), node);
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    /**
     * 移除server节点
     *
     * @param node
     */
    public void remove(T node) {
        lock.writeLock().lock();
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                circle.remove(hashFunction.hashString(node.toString() + i, Charsets.UTF_8).asInt());
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    /**
     * 获取client对应server节点
     *
     * @param key
     * @return
     */
    public T get(Object key) {
        lock.readLock().lock();
        try {
            if (circle.isEmpty()) {
                return null;
            }

            //生成client对应的hash值
            int hash = hashFunction.hashString(key.toString(), Charsets.UTF_8).asInt();

            //如果没有对应此hash的server节点，获取大于等于此hash后面的server节点；如果还没有，则获取server节点分布圆的第一个节点
            if (!circle.containsKey(hash)) {
                SortedMap<Integer, T> tailMap = circle.tailMap(hash);
                hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
            }
            return circle.get(hash);
        } finally {
            lock.readLock().unlock();
        }

    }

    /********************** 测试代码**********start ************************/

    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> nodeList = new ArrayList<>();
        nodeList.add("www.google.com.hk");
        nodeList.add("www.apple.com.cn");
        String node = "www.twitter.com";
        nodeList.add(node);

        HashFunction hf = Hashing.md5();

        ConsistentHash<String> consistentHash = new ConsistentHash<>(hf, 1000, nodeList);
        //根据一致性hash算法获取客户端对应的服务器节点


        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            testSpeed(consistentHash, node, true);
            latch.countDown();
        }).start();
        new Thread(() -> {
            testSpeed(consistentHash, node, false);
            latch.countDown();
        }).start();
        latch.await();

    }

    private static void testSpeed(ConsistentHash<String> consistentHash, String node, boolean del) {
        long start = System.currentTimeMillis();

        Map<String, Integer> result = Maps.newHashMap();

        for (int i = 0; i < 100000; i++) {
            String url = consistentHash.get(RandomStringUtils.random(12));
            if (result.get(url) != null) {
                result.put(url, result.get(url) + 1);
            } else {
                result.put(url, 0);
            }
            if (i % 300 == 0) {
                if (del)
                    consistentHash.remove(node);
                else consistentHash.add(node);
            }
        }
        System.out.println(Joiner.on(" ; ").withKeyValueSeparator(" = ").join(result));
        System.out.println(System.currentTimeMillis() - start);
    }


}