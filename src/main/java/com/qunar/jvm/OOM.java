package com.qunar.jvm;

import java.util.ArrayList;

public class OOM {
    static class OOMObject {

    }

    public static void main(String[] args) {

        ArrayList<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
