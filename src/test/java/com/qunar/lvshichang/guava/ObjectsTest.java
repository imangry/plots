package com.qunar.lvshichang.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.junit.Test;

public class ObjectsTest {


    @Test
    public void objectTest(){
        boolean equal = Objects.equal(Integer.valueOf(1), 1);
        System.out.println(equal);

        Integer integer = MoreObjects.firstNonNull(1, null);
        System.out.println(integer);

        int i = Objects.hashCode(1, 2, 3);
        System.out.println(i);
    }


}
