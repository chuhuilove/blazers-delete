package com.chuhui.blazers.algorithm;

import sun.security.action.GetBooleanAction;

import java.security.AccessController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * LRUAlgorithm Lru算法
 *
 * @author: 纯阳子
 * @Date: 2019/4/28
 * @Description:TODO
 */
public class LRUAlgorithm {

    protected static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {

        System.err.println("开始时间:"+ LocalDateTime.now().format(formatter));
//"xcccyzi".intern()

        List<String> lists = IntStream.rangeClosed(1, 20000).parallel().mapToObj(e -> UUID.randomUUID().toString().replace("-", "").substring(20)).collect(Collectors.toList());
        System.err.println("结束时间:"+ LocalDateTime.now().format(formatter));


        System.err.println("开始排序时间:"+ LocalDateTime.now().format(formatter));

        lists.sort(null);

        System.err.println("结束排序时间:"+ LocalDateTime.now().format(formatter));

//        Boolean aBoolean = AccessController.doPrivileged(
//                new GetBooleanAction(
//                        "java.util.Arrays.useLegacyMergeSort"));
//
//        System.err.println(aBoolean);

        function();
    }


    public static void function(){
        if(1==1){
            function();
        }
    }



}
