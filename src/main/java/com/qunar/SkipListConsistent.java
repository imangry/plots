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
    static class Node<T> {
        private T obj;
        private int weight;

        public Node(T obj, int weight) {
            this.obj = obj;
            this.weight = weight;
        }
    }

    private ConcurrentSkipListMap<Integer, T> skipListMap = new ConcurrentSkipListMap<>();

    public SkipListConsistent() {
    }

    public SkipListConsistent(List<Node<T>> nodes) {
        for (Node<T> node : nodes) {
            addNode(node);
        }
    }


    public void addNode(Node<T> node) {
        for (int i = 0; i < node.weight; i++) {
            skipListMap.put(Hashing.murmur3_32()
                    .hashString(node.obj.toString() + i, Charsets.UTF_8)
                    .asInt(), node.obj);
        }
    }

    public void delNode(Node<T> node) {
        for (int i = 0; i < node.weight; i++) {
            skipListMap.remove(Hashing.murmur3_32()
                    .hashString(node.obj.toString() + i, Charsets.UTF_8)
                    .asInt());
        }
    }

    public T get(Object key) {
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

        consistent.addNode(new Node<>("www.google.com", 1000));
        Node<String> node = new Node<>("www.baidu.com", 1000);
        consistent.addNode(node);
        Node<String> node1 = new Node<>("www.sogo.com", 1000);
        consistent.addNode(node1);

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            testSpeed(consistent, node, true);
            latch.countDown();
        }).start();
        new Thread(() -> {
            testSpeed(consistent, node, false);
            latch.countDown();
        }).start();
        latch.await();
    }

    private static void testSpeed(SkipListConsistent<String> consistent, Node<String> node, boolean del) {
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
                    consistent.delNode(node);
                else
                    consistent.addNode(node);
            }
        }

        System.out.println(Joiner.on(" ; ").withKeyValueSeparator(" = ").join(result));
        System.out.println(System.currentTimeMillis() - start);
    }

}
