package com.chuhui.blazers.collection.sorted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * CollectionSort
 *
 * @author: cyzi
 * @Date: 2019/7/31 0031
 * @Description:TODO
 */
public class CollectionSort {

    public static void main(String[] args) {

        List<Integer> sortList = new ArrayList<>();
        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " 开始添加数据:");
        IntStream.rangeClosed(1, 20000000).forEach(e -> sortList.add(ThreadLocalRandom.current().nextInt()));

        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " 开始排序:" + sortList.size());
        sortList.sort(Integer::compareTo);

Collections.sort();


        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " 结束排序:" + sortList.size());
    }
}
