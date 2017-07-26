package com.qunar.lvshichang.test;

public class StringTest {


    public static void main(String[] args) {
        String ab = new String("a") + new String("b");
        String ab2 = "ab";
        System.out.println(ab.intern() == ab2);
        System.out.println(ab == ab2);

        String cd = new String("c") + new String("d");
        cd.intern();
        String cd2 = "cd";
        System.out.println(cd == cd2);
    }
}
