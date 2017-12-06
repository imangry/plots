package com.qunar.lvshichang.socket;

import com.google.common.base.Charsets;
import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class KevinSocketClient {

    public static void main(String[] args) throws IOException {
        connectKevin();
    }

    public static void connectKevin() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9998);
        socket.setSoTimeout(10000000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Thread readThread = new Thread() {
            @Override
            public void run() {
                try {
                    BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charsets.UTF_8));
                    String line = null;
                    while ((line = r.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        readThread.start();
        int flag = 0;
        while (true) {
            String line = reader.readLine();
            switch (line) {
                case "byte":
                    flag = 1;
                    continue;
                case "str":
                    flag = 2;
                    continue;
                case "num":
                    flag = 3;
                    continue;
            }
            switch (flag) {
                case 1:
                    Integer value = Integer.valueOf(line);
                    System.out.println(value);
                    outputStream.write(value);
                    outputStream.flush();
                    break;
                case 2:
                    outputStream.write(line.getBytes(Charsets.UTF_8));
                    outputStream.flush();
                    break;
                case 3:
                    Integer val = Integer.valueOf(line);
                    System.out.println(val);
                    ByteBuffer buffer = ByteBuffer.allocate(4);
                    buffer.putInt(val);

                    byte[] array = buffer.array();
                    System.out.println(Arrays.toString(array));
                    outputStream.write(array);
                    outputStream.flush();
                    break;
            }
        }
    }

    @Test
    public void testByteBuffer() throws IOException {
        String str = "Hello world";
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        buffer.put(1, (byte) 'E');

        System.out.println(new String(bytes));

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));

        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer allocate = ByteBuffer.allocate(10);
        int read = socketChannel.read(allocate);
        allocate.flip();

        while (allocate.hasRemaining()) {
            byte c = allocate.get();
            System.out.println((char) c);
        }
        allocate.clear();
    }

    @Test
    public void testConnect() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 80));
        ByteBuffer buffer = ByteBuffer.allocate(10);
        socketChannel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println((char) b);
        }
    }


    @Test
    public void testSelect() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9988));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select();
            if (select <= 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("accept :" + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                ByteBuffer buffer = ByteBuffer.allocate(10);
                if (selectionKey.isReadable()) {
                    for (int i = 0; i < 3; i++) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        channel.read(buffer);
                    }
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println(buffer.get());
                }
                selectionKey.cancel();
                iterator.remove();
            }
        }
    }

    @Test
    public void testFileChannel() {
        System.currentTimeMillis();
    }


    @Test
    public void testContinue(){
        int i = 1;
        switch (i) {
            case 1:
//                continue;
        }
    }
}
