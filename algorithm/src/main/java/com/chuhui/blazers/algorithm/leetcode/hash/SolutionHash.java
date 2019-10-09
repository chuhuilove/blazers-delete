package com.chuhui.blazers.algorithm.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * SolutionHash
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/10/9
 */
public class SolutionHash {

    /**
     * 1 两数之和
     *
     * 我的解题:
     * https://leetcode-cn.com/problems/two-sum/solution/li-yong-hashmapcun-chu-he-chai-by-chuhuilove/
     *
     * @param nums   给定数组
     * @param target 目标值
     * @return 返回数组
     */
    static int[] twoSum(int[] nums, int target) {

        // 差,下标
        Map<Integer, Integer> map = new HashMap<>(16);

        for (int i = 0; i < nums.length; i++) {

            int difference = target - nums[i];

            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(difference, i);
            }
        }

        return null;
    }

}
