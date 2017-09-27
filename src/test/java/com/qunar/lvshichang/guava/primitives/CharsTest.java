package com.qunar.lvshichang.guava.primitives;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharsTest {

    @Test
    public void toBytes() throws UnsupportedEncodingException {
        System.out.println("欣".getBytes("UTF-16").length + "\t" + Arrays.toString("欣".getBytes("UTF-16")));
        System.out.println("欣".getBytes("UTF-16BE").length + "\t" + Arrays.toString("欣".getBytes("UTF-16BE")));
        System.out.println("欣".getBytes("UTF-16LE").length + "\t" + Arrays.toString("欣".getBytes("UTF-16LE")));
        System.out.println("欣".getBytes("UNICODE").length + "\t" + Arrays.toString("欣".getBytes("UNICODE")));
    }
}
