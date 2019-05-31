package com.chuhui.blazers.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocketServer {

    public static final String FORMATTER_STR = "yyyy-MM-dd hh:mm:ss:SSS";

    private static int counter = 1;

    public static void main(String[] args) throws IOException {


        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("172.16.23.115", 8089));

        System.err.println("the server has started.....");

        while (true) {

            Socket socket
                    = server.accept();

            InputStream is = socket.getInputStream();
            byte[] body = new byte[1024];
            is.read(body);

            OutputStream outputStream = socket.getOutputStream();

            String requestBody = new String(body);

            byte[] respBody = (formatDateTime(FORMATTER_STR) + "--->" + (counter++) + "--->" + requestBody).getBytes();
            outputStream.write(respBody);

        }
    }

    public static String formatDateTime(String formatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
    }
}
