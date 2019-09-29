package com.chuhui.blazers.algorithm.loadbbalancing.random;

import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils;

import java.util.List;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.INVOKE_NUM;

/**
 * random
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class Random {

    public static void main(String[] args) {

        for (int i = 0; i < INVOKE_NUM; i++) {
            System.err.println(getIp(DataUtils.IP_LIST));
        }
    }

    static String getIp(List<String> lists) {
        return lists.get(new java.util.Random().nextInt(lists.size()));
    }

}
