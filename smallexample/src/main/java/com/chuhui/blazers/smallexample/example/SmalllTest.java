package com.chuhui.blazers.smallexample.example;

import java.sql.DriverManager;
import java.util.Arrays;

public class SmalllTest {


    public static final String OCPHeaderTag = "<QMDB>";

    public static void main(String[] args) {


        int  a=Integer.MAX_VALUE-10;


        System.err.println( Integer.toBinaryString(a));

        int b=a>>>16;

        System.err.println( Integer.toBinaryString(b));

        System.err.println(b);

        int c=a^b;
        System.err.println("异或后的结果:"+c);
        System.err.println( Integer.toBinaryString(c));


        System.err.println("实际值:"+hash(a));


    }

    static   int hash(Integer key) {
        int h=key;
        return  (h = key.hashCode()) ^ (h >>> 16);
    }




    static void checkIndexOf() {
        String str = "12321323112313131";

        int headerTagPosition = str.indexOf(OCPHeaderTag);

        boolean isValidPacket = headerTagPosition != -1;

        if (isValidPacket) {
            System.err.println("存在:" + OCPHeaderTag);
        } else {
            System.err.println("不存在:" + OCPHeaderTag);
        }


        int[] oldArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11};
        int[] newArr = new int[oldArr.length];

        System.arraycopy(oldArr, 2, newArr, 0, 5);

        Arrays.stream(newArr).forEach(System.err::println);

    }


}
