package com.qunar.jvm;

import java.util.Scanner;

public class TestGC {
    private final static int int_1MB = 1024 * 1024 * 1024;

    static class ReferenceCountingGC {
        public Object instance = null;
    }

    public static void main(String[] args) {
        ReferenceCountingGC a = new ReferenceCountingGC();
        ReferenceCountingGC b = new ReferenceCountingGC();
        a.instance = b;
        b.instance = a;

        a = null;
        b = null;
        System.gc();

        Scanner scanner = new Scanner(System.in);
        scanner.nextByte();
    }


}
