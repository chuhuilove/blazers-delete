package com.chuhui.blazers.collection.map;

import java.util.HashMap;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
public class CustomerHashMap<K,V>  {


    public static  int hash(Object obj){
        return obj.hashCode();
    }

    public boolean put(K k,V v){
        hash(k);
        return true;
    }




}
