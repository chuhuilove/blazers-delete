package com.chuhui.blazers.socket.stickypack.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * 分析socket的粘包与拆包问题
 */
public class SocketServer {


    private static int counter = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9007);
        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat)+"the server has started.....");

        while (true) {
            Socket socket
                    = server.accept();
            System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " server received request");

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

                System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + "received data:" + requestBody);

                byte[] respBody = (returnCurrentTimeFormated(commonlyUserDateTimeFormat) + "--->" + (counter++) + "--->" + requestBody).getBytes();
                outputStream.write(respBody);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
