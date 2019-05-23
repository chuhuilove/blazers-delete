package com.chuhui.blazers.smallexam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class SmalllTest {


    public static final String OCPHeaderTag = "<QMDB>";

    public static void main(String[] args) {



        String str="12321323112313131";

        int headerTagPosition = str.indexOf(OCPHeaderTag);

        boolean isValidPacket = headerTagPosition != -1;

        if(isValidPacket){
            System.err.println("存在:"+OCPHeaderTag);
        }else{
            System.err.println("不存在:"+OCPHeaderTag);
        }


        int [] oldArr=new int[]{1,2,3,4,5,6,7,8,9,11};
        int [] newArr=new int[oldArr.length];

        System.arraycopy(oldArr,2,newArr,0,5);

        Arrays.stream(newArr).forEach(System.err::println);



    }

}
