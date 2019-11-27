package com.chuhui.blazers.socket.aionio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;
import static com.chuhui.blazers.socket.aionio.BIOServer.SERVER_PORT;

/**
 * BIOClient
 *
 * @author: cyzi
 * @Date: 2019/11/27 0027
 * @Description:TODO
 */
public class BIOClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", SERVER_PORT), 1000);
        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + "客户端连接已经建立");
        sendBigDataPackage(socket);
    }

    static void sendBigDataPackage(Socket socket) throws IOException {


        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        InputStream inputStream = socket.getInputStream();

        String fileName = "BIOClient-received.txt";

        Path receivedFilePath = Paths.get(fileName);

        Files.deleteIfExists(receivedFilePath);
        Files.createFile(receivedFilePath);


        BufferedWriter writer = Files.newBufferedWriter(receivedFilePath);


        for (; ; ) {
            System.err.println("please input send to server data count:");
            int loopCount = scanner.nextInt();

            byte[] readBytes = new byte[256];
            for (int i = 1; i <= loopCount; i++) {
                outputStream.write(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
                outputStream.flush();

                int readLength = inputStream.read(readBytes, 0, readBytes.length);

                String s = new String(readBytes, 0, readLength, StandardCharsets.UTF_8);
                writer.write("from server data is:" + s);
                writer.newLine();
            }
        }
    }

    /**
     * 可以正常的和服务端的handlerData函数进行交互
     *
     * @param socket
     * @throws IOException
     */
    static void writeAndReadDataWithServer(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.err.println("please input your data:");
            String requestStr = scanner.nextLine();
            outputStream.write(requestStr.getBytes(StandardCharsets.UTF_8));

            byte[] readBytes = new byte[1024];

            InputStream inputStream = socket.getInputStream();
            int readLength = inputStream.read(readBytes);

            String s = new String(readBytes, 0, readLength);

            System.err.println("from server data:" + s);
        }
    }
}