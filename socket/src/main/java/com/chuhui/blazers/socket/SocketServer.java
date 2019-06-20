package com.chuhui.blazers.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocketServer {

    public static final String FORMATTER_STR = "yyyy-MM-dd hh:mm:ss:SSS";

    private static int counter = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9007);
        System.err.println("the server has started.....");

        while (true) {
            Socket socket
                    = server.accept();
            System.err.println(formatDateTime(FORMATTER_STR) + " 服务端接收数据");
            new Thread(() -> new HandleAccept(socket)).start();
        }
    }

    static String formatDateTime(String formatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
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

                System.err.println(formatDateTime(FORMATTER_STR) + " 接收到的数据:" + requestBody);

                byte[] respBody = (formatDateTime(FORMATTER_STR) + "--->" + (counter++) + "--->" + requestBody).getBytes();
                outputStream.write(respBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
