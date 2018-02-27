package com.qunar;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Date: 18/01/14
 * User: lvshi
 */
public class MapMethod {


    public static void main(String[] args) {
        Map<String, Integer> map = Maps.newHashMap();

        for (int i = 0; i < 100; i++) {
            map.put("key", map.getOrDefault("key", 0) + 1);
        }
        System.out.println(map.get("key"));
    }
}
