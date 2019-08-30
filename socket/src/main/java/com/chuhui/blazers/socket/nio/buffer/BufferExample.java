package com.chuhui.blazers.socket.nio.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferExample {


    public static void main1(String[] args) {

        ByteBuffer buffer = bufferGen();

        buffer.get();
        buffer.get();
        buffer.get();
        buffer.get();
        buffer.get();


        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();


        System.err.println(readOnlyBuffer);

        byte[] tag = new byte[6];

        buffer.get(tag, 0, 6);

        System.err.println("tag:" + new String(tag, Charset.forName("UTF-8")));

        tag = new byte[16];
        buffer.get(tag, 0, 16);

        System.err.println("key:" + new String(tag, Charset.forName("UTF-8")));


        System.err.println("version:" + buffer.get());
        System.err.println("length:" + buffer.getInt());
        System.err.println("commandCode:" + buffer.getShort());


        System.err.println("sessionId:" + (buffer.getInt() & 0xffffffffL));

        tag = new byte[6];
        buffer.get(tag, 0, 6);
        System.err.println("date-time:" + new String(tag, Charset.forName("UTF-8")));

        System.err.println("sequenceNo:" + buffer.getInt());


    }


    public static ByteBuffer bufferGen() {


        byte[] bytes = new byte[]{60, 81, 77, 68, 66, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 66, 0, -56, 0, 0, 0, 120, 0, 48, 48, 48, 48, 48, 0, 0, 0, 1, 0, 4, 0, 8, 0, 0, 0, 0, 0, 2, 0, 8, 0, 0, 0, 0, 0, 3, 0, 7, 79, 75, 0};


        ByteBuffer result = ByteBuffer.allocate(1024);
        result.put(bytes, 0, bytes.length);

        result.flip();


        return result;

    }

    public static void main(String[] args) {

        try {
            testBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 采用字节流 一个字节一个字节的读写
     */
    public static void testBufferOne() throws IOException {

        InputStream is = new FileInputStream("input.txt");
        OutputStream os = new FileOutputStream("output.txt");

        int readBytes;
        while ((readBytes = is.read()) != -1) {
            System.err.println("read size:" + readBytes);
            os.write(readBytes);
        }
        // 关闭省略
    }


    public static void testBuffer() throws IOException {

        InputStream is = new FileInputStream("input.txt");
        OutputStream os = new FileOutputStream("output.txt");

        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);


        byte[] readArr = new byte[1024];
        int readBytes;
        while ((readBytes = bis.read(readArr)) != -1) {
            System.err.println("read size:" + readBytes);
            bos.write(readArr);
            readArr = new byte[1024];
        }
        bis.close();
        bos.close();
    }

    public static void testLine() throws IOException {

        FileWriter fileWriter = new FileWriter("output.txt");
        FileReader fileReader = new FileReader("input.txt");

        BufferedWriter bw = new BufferedWriter(fileWriter);
        BufferedReader br = new BufferedReader(fileReader);

        String temp;
        while ((temp = br.readLine()) != null) {
            System.err.println("read data:" + temp);
            bw.write(temp);
            bw.newLine();
        }
        // 关闭省略
    }


}
