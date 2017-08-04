package com.qunar.lvshichang.test;


public class Test {


    public static void main(String[] args) {

//        拆装箱 TODO
        Object o1 = true ? new Integer(1) : new Double(3.0);
        System.out.println(o1);


        String str = new String("aaa");

        str.intern();
        System.out.println();

    }


}
