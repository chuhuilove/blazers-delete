package com.chuhui.blazers.algorithm.leetcode.math;

/**
 * SolutionMath
 *
 * @author: cyzi
 * @Date: 2019/10/9 0009
 * @Description:TODO
 */
public class SolutionMath {

    /**
     * 441. 排列硬币
     * 直接去看解题吧,妈的.提交了10次才通过.
     *
     * https://leetcode-cn.com/problems/arranging-coins/solution/
     * @param n
     * @return
     */
    static int arrangeCoins(int n) {

        double n1 = n;
        // (1 + k) * k / 2 > n
        long k = (long) Math.sqrt((n1 * 2));
        if (k * k + k > n1 * 2) {
            return (int) k - 1;
        }
        return (int) k;
    }

}
