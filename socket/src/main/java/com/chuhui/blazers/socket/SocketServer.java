package com.chuhui.blazers.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocketServer {

    public static final String FORMATTER_STR = "yyyy-MM-dd hh:mm:ss:SSS";

    private static int counter = 1;

    public static void main(String[] args) throws IOException {


        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 8081));

        while (true) {

            Socket socket
                    = server.accept();

            InputStream is = socket.getInputStream();
            byte[] body = new byte[102400];
            is.read(body);

            System.err.println(formatDateTime(FORMATTER_STR) + "--->" + (counter++)
                    + "--->" + new String(body, "UTF-8"));

        }
    }

    public static String formatDateTime(String formatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
    }


}
