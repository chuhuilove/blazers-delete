package com.chuhui.blazers.socket.stickypack.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

/**
 * 分析socket的粘包与拆包问题
 * 文档：TCP粘包与拆包详解.md
 * 链接：http://note.youdao.com/noteshare?id=335ab317899c199f048dde2a51e869cf&sub=D6413BA8AF83437F8D4AA3D58EF50BC5
 */
public class SocketServerForLinux {
    static final DateTimeFormatter commonlyUserDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    private static int counter = 1;

    private static int BYTESUM = 17000 + (9 * 1 + 2 * 90 + 3 * 900 + 4 * 1);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9009);

        System.err.println(formatDateTime() + "the server has started.....");

        while (true) {
            Socket socket
                    = server.accept();
            System.err.println(formatDateTime() + " server received request");

            new Thread(new HandleAccept(socket)).start();
        }
    }


    static protected class HandleAccept implements Runnable {

        private Socket socket;


        public HandleAccept(Socket socket) {
            System.err.println(formatDateTime() + " create HandleAccept object");
            this.socket = socket;
        }

        @Override
        public void run() {
            System.err.println("start thread:" + Thread.currentThread().getName());
            try {

                InputStream is = socket.getInputStream();

                /**
                 * 2019年7月30日19:44:22
                 * 粘包场景模拟:
                 * 和该服务端配套的客户端,每次只发17+[1,4]个字节,连续发1000次,一共17000+(9*1+2*90+3*900+4*1)=19893个字节
                 *
                 * 粘包与拆包问题描述与详解:
                 *
                 * 因为TCP是稳定连接,网络连接正常情况下不会出现丢包的情况.
                 *
                 * 1. 将接收缓冲区扩大到19893个字节
                 *
                 *     数据部分收到,会收到的部分,出现了粘包现象`.但是数据全部连接在一起了,由于字节数过大,会产生拆包
                 *
                 *
                 * 2. 将缓冲区设置成500个字节
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 */

                byte[] body = new byte[BYTESUM];

                int readBytes = is.read(body);

                System.err.println("the first read byte size=" + readBytes);


                if(readBytes<BYTESUM){

                   byte[] body2=new byte[BYTESUM-readBytes];

                    readBytes = is.read(body2);

                    System.err.println("the second read byte size=" + readBytes);



                }

                String requestBody = new String(body);

                System.err.println(formatDateTime() + "received data:" + requestBody);

                OutputStream outputStream = socket.getOutputStream();

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
