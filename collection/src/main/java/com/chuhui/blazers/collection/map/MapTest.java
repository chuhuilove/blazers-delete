package com.chuhui.blazers.collection.map;

import java.sql.Wrapper;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Map
 *
 * @author: 纯阳子
 * @Date: 2019/4/10
 * @Description:TODO
 */
public class MapTest {


    public static void testOrder(){

        /**
         * HashMap是无序的(插入顺序和自然顺序); 特别是,其顺序会随着时间的推移会自行改变(重新hash).
         */

        Map<String,Integer> map=new HashMap<>();

        System.err.println("start test Map.put");
        System.err.println("start test Map.put");
        System.err.println("start test Map.put");


        for(int i=1;i<=40;i++){
            map.put(i+"chuhui",1);
        }

        System.err.println("start test Map.put");
        System.err.println("start test Map.put");
        System.err.println("start test Map.put");

        map.keySet().stream().forEach(e-> System.err.println(e+"---->"+map.get(e)));


    }


    public static void main(String[] args) {

        testOrder();

    }

}
