package com.chuhui.blazers.algorithm.leetcode;

import java.math.BigDecimal;

/**
 * BigNumCompute
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/8/20
 * @Description: 大数计算
 */
public class BigNumCompute {


    public static void main(String[] args) {


        /**
         * 从 BigDecimal 中抽取出来一些算法
         */

        BigDecimal bigDecimal = new BigDecimal("123456789876123123215435435333645473213232124343254234535434435435345345435435443535534312334");


        BigDecimal bigDecimal1 = new BigDecimal("987223344556233232432242354543534543534543543554353454355435435543543543424423432432465443212234567787655545");


        BigDecimal multiply = bigDecimal1.multiply(bigDecimal);


        System.err.println(multiply.toString());


    }


}
