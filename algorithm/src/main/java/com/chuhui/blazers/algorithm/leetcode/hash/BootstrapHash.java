package com.chuhui.blazers.algorithm.leetcode.hash;

import java.util.Arrays;

import static com.chuhui.blazers.algorithm.leetcode.hash.SolutionHash.twoSum;

/**
 * BootstrapHash
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/10/9
 */
public class BootstrapHash {


    public static void main(String[] args) {

        function1();
    }

    static void function1() {

        int[] ints = {2, 7, 11, 15};
        System.err.println(Arrays.toString(twoSum(ints, 9)));

    }

}

