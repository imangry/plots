package com.qunar.lvshichang.test;


public class Test {


    public static void main(String[] args) {
        try {
            test();

        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }


    public static void test(){
        throw new IllegalArgumentException();
    }

}
