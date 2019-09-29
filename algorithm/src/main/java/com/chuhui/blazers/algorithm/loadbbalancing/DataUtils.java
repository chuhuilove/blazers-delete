package com.chuhui.blazers.algorithm.loadbbalancing;

import java.util.ArrayList;
import java.util.List;

/**
 * DataUtils
 * <p>
 * 产生数据的工具
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class DataUtils {

    public static List<String> IP_LIST = new ArrayList<>();




    static {

        for (int i = 0; i < 10; i++) {
            IP_LIST.add("192.168.0." + i);
        }

    }


}
