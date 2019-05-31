package com.chuhui.blazers.socket.nio.buffer;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.charset.Charset;

public class BufferExample {


    public static void main(String[] args) {

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

        System.err.println("tag:"+new String(tag, Charset.forName("UTF-8")));

        tag=new byte[16];
        buffer.get(tag,0,16);

        System.err.println("key:"+new String(tag, Charset.forName("UTF-8")));


        System.err.println("version:"+buffer.get());
        System.err.println("length:"+buffer.getInt());
        System.err.println("commandCode:"+buffer.getShort());


        System.err.println("sessionId:"+(buffer.getInt()& 0xffffffffL));

        tag=new byte[6];
        buffer.get(tag,0,6);
        System.err.println("date-time:"+new String(tag,Charset.forName("UTF-8")));

        System.err.println("sequenceNo:"+buffer.getInt());
//





    }


    public static ByteBuffer bufferGen() {


        byte[] bytes = new byte[]{60, 81, 77, 68, 66, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 66, 0, -56, 0, 0, 0, 120, 0, 48, 48, 48, 48, 48, 0, 0, 0, 1, 0, 4, 0, 8, 0, 0, 0, 0, 0, 2, 0, 8, 0, 0, 0, 0, 0, 3, 0, 7, 79, 75, 0};


        ByteBuffer result = ByteBuffer.allocate(1024);
        result.put(bytes, 0, bytes.length);

        result.flip();

        return result;

    }


}
