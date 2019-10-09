package com.chuhui.blazers.algorithm.leetcode.bitmanipulation;


import static com.chuhui.blazers.algorithm.leetcode.bitmanipulation.SolutionBitManipulation.singleNumber136;
import static com.chuhui.blazers.algorithm.leetcode.bitmanipulation.SolutionBitManipulation.singleNumber137;

/**
 * SolutionClass
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class BootstrapBitManipulation {

    public static void main(String[] args) {

        function136();
        function137();

    }

    static void function136() {
        int[] arrays = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        System.err.println(singleNumber136(arrays));
    }

    static void function137() {
        int[] arrays = {10,10,10,11};
        System.err.println(singleNumber137(arrays));
    }

}
