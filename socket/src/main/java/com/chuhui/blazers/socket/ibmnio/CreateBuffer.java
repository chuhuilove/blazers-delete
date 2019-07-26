package com.chuhui.blazers.socket.ibmnio;// $Id$

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);


        System.err.println(buffer.get(10));




        buffer.put(10, (byte) 'a');
        System.err.println("put a position:" + buffer.position());
        System.err.println("put a limit:" + buffer.limit());
        buffer.put(11, (byte) 'b');
        System.err.println("put b position:" + buffer.position());
        System.err.println("put b limit:" + buffer.limit());
        buffer.put(12, (byte) 'c');
        System.err.println("put c position:" + buffer.position());
        System.err.println("put c limit:" + buffer.limit());
        buffer.put(13, (byte) 'd');

        System.err.println("put d position:" + buffer.position());
        System.err.println("put d limit:" + buffer.limit());

        byte [] bytes=new byte[]{'z','y','x'};
        buffer.put(bytes);

        System.err.println("put array position:" + buffer.position());
        System.err.println("put array:" + buffer.limit());


        buffer.flip();


        System.err.println("flip position:" + buffer.position());
        System.err.println("flip limit:" + buffer.limit());

//        buffer.limit(buffer.capacity());


        buffer.put(14, (byte) 'e');
        System.err.println("put e position:" + buffer.position());
        System.err.println("put e limit:" + buffer.limit());

        buffer.put(15, (byte) 'f');
        System.err.println("put f position:" + buffer.position());
        System.err.println("put f limit:" + buffer.limit());

        buffer.put(16, (byte) 'g');
        System.err.println("put g position:" + buffer.position());
        System.err.println("put g limit:" + buffer.limit());



        /*
        System.err.println("no put position:" + buffer.position());

        System.err.println("no put limit:" + buffer.limit());


        buffer.put((byte) 'a');

        System.err.println("put a position:" + buffer.position());
        System.err.println("put a limit:" + buffer.limit());

        buffer.put((byte) 'b');
        System.err.println("put b position:" + buffer.position());

        System.err.println("put b limit:" + buffer.limit());

        buffer.put((byte) 'c');

        System.err.println("put c position:" + buffer.position());

        System.err.println("put c limit:" + buffer.limit());



        buffer.flip();
        System.err.println("flip position:" + buffer.position());

        System.err.println("flip limit:" + buffer.limit());

        System.err.println(buffer.get(0));
        System.err.println(buffer.get(1));
        System.err.println(buffer.get(2));

        buffer.put((byte) 'd');
        System.err.println("put d position:" + buffer.position());

        System.err.println("put d limit:" + buffer.limit());

        System.err.println(buffer.get(0));
        System.err.println(buffer.get(1));
        System.err.println(buffer.get(2));
        */


/*
        System.out.println((char) buffer.get());

        System.err.println("get first position:" + buffer.position());
        System.err.println("get first limit:" + buffer.limit());

        System.out.println((char) buffer.get());

        System.err.println("get second position:" + buffer.position());
        System.err.println("get second limit:" + buffer.limit());

        System.out.println((char) buffer.get(2));

        System.err.println("get third position:" + buffer.position());
        System.err.println("get third limit:" + buffer.limit());
        */


    }


    private void printList(List<String> list) {

        for (int i = 0; i < list.size(); i++) {
            System.err.println(list.get(i));
        }
    }

    private List<String> relist(List<String> list) {

        Map<String, Integer> map = new HashMap<>(16);

        for (int i = 0; i < list.size(); i++) {
            if (!map.containsKey(list.get(i))) {
                map.put(list.get(i), 0);
            }
        }
        List<String> newList = new ArrayList<>(map.size());

        map.forEach((e, v) -> newList.add(e));

        return newList;
    }


    private static void printMark(ByteBuffer buffer, String message) {
        try {
            Field mark = ByteBuffer.class.getSuperclass().getDeclaredField("mark");

            mark.setAccessible(true);
            System.err.println(message + mark.get(buffer));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
