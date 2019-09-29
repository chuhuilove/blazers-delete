package com.chuhui.blazers.algorithm.loadbbalancing.random;

import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils;

import java.util.List;

/**
 * random
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class Random {


    public static void main(String[] args) {

        List<String> ipList = DataUtils.IP_LIST;






    }


    static String getIp(List<String>lists){
        return lists.get(new java.util.Random().nextInt(lists.size()));
    }




}
