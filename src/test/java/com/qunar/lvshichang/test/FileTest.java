package com.qunar.lvshichang.test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lvshi\\IdeaProjects\\campus\\src\\test\\resources\\1.txt");
        if (file.exists()) {
            throw new IllegalArgumentException("");
        }
        boolean newFile = file.createNewFile();
    }
}
