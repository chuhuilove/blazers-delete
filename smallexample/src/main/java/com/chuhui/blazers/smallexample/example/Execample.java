package com.chuhui.blazers.smallexample.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Execample
 *
 * @author: 纯阳子
 * @Date: 2019/5/27
 * @Description:TODO
 */
public class Execample {

    //一个java文件中,可以定义n个非嵌套,默认访问修饰的类

    public static void main(String[] args) {


        int a= Integer.MAX_VALUE-10;



        System.err.println(a>>>16);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

        final int maxCount=100000;
        System.err.println("start table:"+LocalDateTime.now().format(formatter));
        final  Hashtable<String, Integer> table=new Hashtable<>();

        IntStream.rangeClosed(1, maxCount).forEach(e -> table.put("cyzi" + e, e));

        IntStream.rangeClosed(1, maxCount).forEach(e -> table.get("cyzi" + e));

        System.err.println("end table:"+LocalDateTime.now().format(formatter));







        System.err.println("start map:"+LocalDateTime.now().format(formatter));

        final Map<String, Integer> map = new HashMap<>();

        IntStream.rangeClosed(1, maxCount).forEach(e -> map.put("cyzi" + e, e));

        IntStream.rangeClosed(1, maxCount).forEach(e -> map.get("cyzi" + e));

        System.err.println("end map:"+LocalDateTime.now().format(formatter));




    }

}
