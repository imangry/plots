package com.qunar.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Date: 18/03/06
 * User: lvshi
 */
public class WriteOOMSlowServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8858));
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        while (true) {
            int read = inputStream.read();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
