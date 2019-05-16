package com.chuhui.blazers.algorithm.interviewguide.chapter4;

import javax.swing.plaf.metal.MetalTheme;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/26 18:55
 * Description:斐波那契函数
 */
public class Fibonacci {


    public static void main(String[] args) {
        int f = bottomUpCutRod(new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 10);
        System.err.println(f);


    }


    public static int f(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    /**
     * 斐波那契非递归
     *
     * @param n
     * @return
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }


        //配合大数相加...

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {

            int thrid = first + second;
            first = second;
            second = thrid;
        }
        return second;

    }

    public static int cutrod(int[] p, int n) {
//
//        if (n == 0) {
//            return 0;
//        }
//
//        int q = Integer.MIN_VALUE;
//
//        for (int i = 1; i <= n; i++) {
//            q = Math.max(q, p[i] + cutrod(p, n - 1));
//        }
//        return q;

        if (n == 0) return 0;
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, p[i - 1] + cutrod(p, n - i));
        }
        return result;

    }

    public static int findArrMaxValue(int nums[]) {

        int maxValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxValue = Math.max(nums[i], maxValue);
        }
        return maxValue;
    }


    public static int memoizedCutRod(int[] prices, int n) {
        int[] arrays = new int[n + 1];

        Arrays.fill(arrays, Integer.MIN_VALUE);

        return memoizedCutRodAux(prices, n, arrays);

    }


    private static int memoizedCutRodAux(int[] prices, int n, int[] arrays) {

        if (arrays[n] >= 0) {
            return arrays[n];
        }

        int q = Integer.MIN_VALUE;
        if (n == 0) {
            q = 0;
        } else {
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, prices[i - 1] + memoizedCutRodAux(prices, n - i, arrays));
            }
            arrays[n] = q;

        }
        return q;
    }


    public static int bottomUpCutRod(int[] prices, int n) {

        int[] arrays = new int[n + 1];
        arrays[0] = 0;

        for (int j = 1; j <= n; j++) {

            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                q = Math.max(q, prices[i - 1] + arrays[j - i]);
            }
            arrays[j] = q;
        }
        return arrays[n];
    }


}
