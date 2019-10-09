package com.chuhui.blazers.algorithm.leetcode.dynamicplanning;

import java.util.Arrays;

/**
 * SolutionDynamicPlanning
 *
 * @author: cyzi
 * @Date: 2019/10/9 0009
 * @Description:TODO
 */
public class SolutionDynamicPlanning {

    /**
     * 198 打家劫舍
     *
     * @param nums 房子
     * @return 获取到的最大金额
     */
    static int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }




        int[] newNums1 = Arrays.copyOf(nums, nums.length - 3);
        int[] newNums2 = Arrays.copyOf(nums, nums.length - 2);
        return Math.max(nums[nums.length - 1] + rob(newNums1), rob(newNums2));
    }


    /**
     * 322 零钱兑换
     * @param coins 硬币种类
     * @param amount 金额
     * @return 需要的最少硬币数量
     */
    public static int coinChange(int[] coins, int amount) {








        return 0;
    }



    /**
     * 算法导论 钢条切割问题
     * @param length
     * @return
     */
    /**
     * @param values 数值
     * @param length
     * @return
     */
    static int cutRod(int[] values, int length) {

        if (length == 1) {
            return values[1];
        }
        if (length == 0) {
            return values[0];
        }
        int q = -1;

        // 为什么我想不到呢?
        for (int i = 1; i <= length; i++) {
            q = Math.max(q, values[i] + cutRod(values, length - i));
        }

        return q;
    }


}
