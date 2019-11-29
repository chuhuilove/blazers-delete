package com.chuhui.blazers.socket.aionio;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * BIOServer
 *
 * @author: cyzi
 * @Date: 2019/11/27 0027
 * @Description:TODO
 */
public class BIOServer {

    static final int SERVER_PORT = 8091;

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(SERVER_PORT);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                // 最大线程池无论设置多少,都不会使用,说白了,这个值已经被禁用了.
                Integer.MAX_VALUE,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new CustomerThreadFactory("BIOServer-"));


        while (true) {
            final Socket clientSocket = server.accept();
            System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + "有新的客户端过来了");
            System.err.println("the uuid length is:" + UUID.randomUUID().toString().length());
            executor.execute(() -> {
                try {
                    handlerDataWithSticky(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }


    /**
     * 客户端与服务端一问一答的情况下,不会出现粘包与拆包
     * 其实,模型还可以继续改造...
     * @param socket
     * @throws IOException
     */
    static void handlerDataWithSticky(Socket socket) throws IOException {

        while (true) {

            int count = 0;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            byte[] readBytes = new byte[128];
            int readLength;
            while ((readLength = bufferedInputStream.read(readBytes, 0, readBytes.length)) != -1) {
                count++;
                String requestStr = new String(readBytes, 0, readLength, StandardCharsets.UTF_8);
                String format = "received from client's data length:" + readLength + ",the number is:" + count + ",the data is:" + requestStr;
                System.err.println(format);
                String responseStr = "received from client's data length:" + readLength + ",the number is:" + count;

                bufferedOutputStream.write(responseStr.getBytes());
                bufferedOutputStream.flush();
            }
        }
    }


    /**
     * 可以正常的可客户端的writeAndReadDataWithServer函数进行交互
     *
     * @param clientSocket
     * @throws IOException
     */
    static void handlerData(Socket clientSocket) throws IOException {


        while (true) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());

            byte[] readBytes = new byte[1024];
            int readLength = bufferedInputStream.read(readBytes, 0, readBytes.length);

            if (readLength == -1) {
                break;
            }
            String requestStr = new String(readBytes, 0, readLength, StandardCharsets.UTF_8);
            System.err.println("read data is :" + requestStr);

            String responseStr = "this is from server:" + UUID.randomUUID().toString();

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
            bufferedOutputStream.write(responseStr.getBytes());
            bufferedOutputStream.flush();
        }

    }

}
