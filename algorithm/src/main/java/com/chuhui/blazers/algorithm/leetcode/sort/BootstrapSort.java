package com.chuhui.blazers.algorithm.leetcode.sort;

import java.util.Arrays;

import static com.chuhui.blazers.algorithm.leetcode.sort.SolutionSort.intersection;

/**
 * BootstrapSort
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/10/8
 */
public class BootstrapSort {

    public static void main(String[] args) {

        function349();


    }


    static void function349() {

        int[] array1 = new int[]{9, 4, 5};
        int[] array2 = new int[]{9, 4, 9, 8, 4};


        System.err.println(Arrays.toString(intersection(array1, array2)));


    }

}
