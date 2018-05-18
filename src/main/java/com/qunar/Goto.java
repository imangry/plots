package com.qunar;

public class Goto {
    public static void main(String[] args) {
        GO:
        for (int i = 0; i < 5; i++) {
            System.out.println(i);

            for (int j = 0; j < 5; j++) {
                System.out.println(i + " " + j);
                if (j == 3) {
                    break GO;
                }
            }
        }
        System.out.println("over");

    }
}
