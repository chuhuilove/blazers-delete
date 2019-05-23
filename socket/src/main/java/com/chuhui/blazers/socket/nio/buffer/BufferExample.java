package com.chuhui.blazers.socket.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class BufferExample {


    public static void main(String[] args) {

        ByteBuffer buffer = bufferGen();

        byte [] tag=new byte[6];

        buffer.get(tag,0,6);
        buffer.rewind();
        System.err.println(new String(tag, Charset.forName("UTF-8")));

    }


    public static ByteBuffer bufferGen() {

        byte[] bytes = new byte[]{60, 81, 77, 68, 66, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 66, 0, -56, 0, 0, 0, 120, 0, 48, 48, 48, 48, 48, 0, 0, 0, 1, 0, 4, 0, 8, 0, 0, 0, 0, 0, 2, 0, 8, 0, 0, 0, 0, 0, 3, 0, 7, 79, 75, 0};

        ByteBuffer result = ByteBuffer.allocate(1024);
        result.put(bytes, 0, bytes.length);
        return result;

    }


}
