package com.chuhui.blazers.algorithm.leetcode.dynamicplanning;

import static com.chuhui.blazers.algorithm.leetcode.dynamicplanning.SolutionDynamicPlanning.cutRod;
import static com.chuhui.blazers.algorithm.leetcode.dynamicplanning.SolutionDynamicPlanning.rob;

/**
 * BootstrapDynamicPlanning
 *
 * @author: cyzi
 * @Date: 2019/10/9 0009
 * @Description:TODO
 */
public class BootstrapDynamicPlanning {

    public static void main(String[] args) {
        functionCutRod();
    }


    static void function198(){

        int[] nums = {2, 7, 9, 3, 1};
        System.err.println(rob(nums));
    }

    static void functionCutRod(){
          final int[] values = new int[]{0,1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        System.err.println(cutRod(values,10));
    }

}


