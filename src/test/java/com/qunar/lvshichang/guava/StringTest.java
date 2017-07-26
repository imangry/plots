package com.qunar.lvshichang.guava;

import org.junit.Test;

public class StringTest {


    @Test
    public void stringIntern() {

        String aaa = "aaa";
        String aaa1 = new String("aaa");
        String aaa2 = aaa1.intern();
        System.out.println(aaa == aaa1);
        System.out.println(aaa == aaa2);

    }
}
