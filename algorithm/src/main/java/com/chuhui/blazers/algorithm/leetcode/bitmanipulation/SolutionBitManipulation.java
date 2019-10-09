package com.chuhui.blazers.algorithm.leetcode.bitmanipulation;

/**
 * Solution
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class SolutionBitManipulation {


    /**
     * 136 只出现一次的数字
     * <p>
     * 对于异或来说:
     * <p>
     * 相同的数字异或结果为零
     * 零与任何数字异或结果都是那个数字
     *
     * @param nums
     * @return
     */
    static int singleNumber136(int[] nums) {

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    /**
     * 137 只出现一次的数字
     * 黑人问号???
     * 这怎么解答
     * @param nums
     * @return
     */
    static int singleNumber137(int[] nums) {

        int res = 0;

        for (int i = 0; i < nums.length-1; i++) {
            res = res ^ nums[i] & nums[i + 1];
        }

        return res;
    }


    /**
     * 169 求众数
     *
     * 如果使用map,那也太low了
     *
     * @param nums
     * @return
     */
    static int majorityElement(int [] nums){


        




        return 0;
    }



}
