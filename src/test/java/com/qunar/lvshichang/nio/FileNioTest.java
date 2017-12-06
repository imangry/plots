package com.qunar.lvshichang.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNioTest {

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\lvshi\\Desktop\\ex.txt", "rw")) {
            FileChannel channel = randomAccessFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(24);
            int count = 0;
            while ((count = channel.read(buf)) != -1) {
                buf.flip();

            }


        }
    }


}
