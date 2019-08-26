package com.chuhui.blazers.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * GoogleBloomFilter
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/8/26
 * @Description:TODO
 */
public class GoogleBloomFilter {

    private static int size = 100000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.001);

    public static void main(String[] args) {


        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<>(10000);

        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.err.println("误判的数量:" + list.size());

    }

}
