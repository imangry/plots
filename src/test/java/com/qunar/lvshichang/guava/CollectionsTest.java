package com.qunar.lvshichang.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.TreeMultiset;
import org.junit.Test;

/**
 * Created by lvshi on 17/07/17.
 */
public class CollectionsTest {

    @Test
    public void multiSet() {

//        HashMultiset<String> set = HashMultiset.create();
//        set.add("1");
//        set.add("2");
//        set.add("2");
//        set.add("3");
//        set.add("3");
//        set.add("3");
//        set.add("3");
//        System.out.println("count 2:" + set.count("2"));
        TreeMultiset<String> set = TreeMultiset.create();

        set.add("3");
        set.add("3");
        set.add("3");
        set.add("3");
        set.add("1");
        set.add("2");
        set.add("2");
        set.add("apple");
        set.add("lvshichang");
        set.add("aqqle");
        for (String next : set) {
            System.out.println(next);
        }
    }

    @Test
    public void test1()
    {
        BiMap<Integer, String> map = HashBiMap.create();
        map.put(1, "0");
        map.put(2, "0");
        map.put(1, "1");
        System.out.println(map.toString());
        System.out.println(map.inverse().toString());
        System.out.println(map.toString());
    }
}
