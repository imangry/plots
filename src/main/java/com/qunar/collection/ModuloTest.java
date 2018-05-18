package com.qunar.collection;

import java.util.Random;

public class ModuloTest {

    public static void main(String[] args) {
        int i = 32;
        int j = 64;
        Random random = new Random();
        for (int k = 0; k < 100; k++) {
            int tmp = Math.abs(random.nextInt());
            int a = tmp & (i - 1);
            int b = tmp & (j - 1);
            System.out.println(a +"  " + b);
        }

    }
}
