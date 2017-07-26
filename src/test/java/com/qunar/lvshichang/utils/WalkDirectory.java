package com.qunar.lvshichang.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lvshi on 17/07/17.
 */
public class WalkDirectory {


    public static void main(String[] args) {
//        List<File> files = listDirectoryFiles(new File("."));
        List<File> files = listFilesByLayerWalk(new File("."));

        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * 层次遍历目录，获得文件
     * @param base
     * @return
     */
    public static List<File> listFilesByLayerWalk(File base) {
        ArrayList<File> result = new ArrayList<File>();

        if (base == null || !base.exists() || !base.isDirectory()) {
            return result;
        }
        LinkedList<File> dirs = new LinkedList<File>();
        dirs.add(base);
        while (dirs.size() > 0) {
            File first = dirs.removeFirst();
            File[] files = first.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        dirs.add(file);
                        continue;
                    }
                    result.add(file);
                }
            }
        }
        return result;
    }

    /**
     * 递归遍历目录，获得文件
     * @param base
     * @return
     */
    public static List<File> listDirectoryFiles(File base) {
        List<File> files = new ArrayList<File>();
        recursionDirecotry(files, base);
        return files;
    }

    private static void recursionDirecotry(List<File> files, File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] currentFiles = dir.listFiles();
        if (currentFiles != null) {
            for (File file : currentFiles) {
                if (file.isDirectory()) {
                    recursionDirecotry(files, file);
                    continue;
                }
                files.add(file);
            }
        }
    }
}
