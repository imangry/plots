package com.qunar.lvshichang.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class First {


    @Test
    public void testSort() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 6, 4);
        list.sort(Integer::compareTo);
        System.out.println(Arrays.toString(list.toArray()));
    }


}
