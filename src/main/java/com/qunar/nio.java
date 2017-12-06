package com.qunar;

import com.google.common.base.Charsets;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class nio {
    public static void main(String[] args) throws IOException {
        File temp = File.createTempFile("holy", null);
        RandomAccessFile file = new RandomAccessFile(temp, "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        putData(0, byteBuffer, fileChannel);
        putData(50000, byteBuffer, fileChannel);

        System.out.println(temp.getAbsolutePath() + "  " + fileChannel.size());
        fileChannel.close();
        file.close();
    }

    private static void putData(int postion, ByteBuffer buffer, FileChannel channel) throws IOException {
        String string = "*<----location" + postion;
        buffer.clear();
        buffer.put(string.getBytes(Charsets.UTF_8));
        buffer.flip();
        channel.position(postion);
        channel.write(buffer);
    }
}
