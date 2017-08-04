package com.qunar.lvshichang.test;

public class GeTest {
    Object obj;
    public void set(Object obj) {
        this.obj = obj;
    }

    public Object get() {
        return obj;
    }

    public static void main(String[] args) {

        GeTest test = new GeTest();

        test.set(new Integer(1000));

        Integer r = (Integer) test.get();

    }
}
