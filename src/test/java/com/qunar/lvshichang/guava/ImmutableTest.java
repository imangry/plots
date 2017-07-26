package com.qunar.lvshichang.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

public class ImmutableTest {



    @Test

    public void immutableBuilder(){

        ArrayList<String> list = Lists.newArrayList("a", "b");

        ImmutableList.builder().addAll(list);
    }
}
