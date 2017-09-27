package com.qunar.lvshichang.test;

import java.util.EnumSet;

public class EnumTest {


    enum Fruit {
        APPLE {
            @Override
            void printName() {
                System.out.println("apple");
            }
        }, BANANA {
            @Override
            void printName() {
                System.out.println("banana");

            }
        };

        abstract void printName();
    }

    public static void main(String[] args) {
        EnumSet<Fruit> fruits = EnumSet.of(Fruit.APPLE, Fruit.BANANA);

        for (Fruit f :
                fruits) {
            f.printName();
        }


    }
}
