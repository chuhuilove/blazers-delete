package com.chuhui.blazers.socket.stickypack.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 分析socket的粘包与拆包问题
 */
public class SocketServerForLinux {
    static final DateTimeFormatter commonlyUserDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");


    private static int counter = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("VM_0_13_centos",9007));

        System.err.println(formatDateTime() + "the server has started.....");

        while (true) {
            Socket socket
                    = server.accept();
            System.err.println(formatDateTime() + " server received request");

            new Thread(() -> new HandleAccept(socket)).start();

        }
    }


    static protected class HandleAccept implements Runnable {

        private Socket socket;


        public HandleAccept(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                byte[] body = new byte[1024];

                is.read(body);
                OutputStream outputStream = socket.getOutputStream();

                String requestBody = new String(body);

                System.err.println(formatDateTime() + "received data:" + requestBody);

                byte[] respBody = (formatDateTime() + "--->" + (counter++) + "--->" + requestBody).getBytes();
                outputStream.write(respBody);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String formatDateTime() {

        return LocalDateTime.now().format(commonlyUserDateTimeFormat);

    }

}
