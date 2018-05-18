package com.qunar.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryOOM {
    private final static int int_1MB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            unsafe.allocateMemory(int_1MB);
        }
    }
}
