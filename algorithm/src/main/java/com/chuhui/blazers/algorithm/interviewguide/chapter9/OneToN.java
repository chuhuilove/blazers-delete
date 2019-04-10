package com.chuhui.blazers.algorithm.interviewguide.chapter9;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/12 9:45
 * Description: 1到n中1出现的次数
 */
public class OneToN {

    /**
     * 如题,从1到10,含有1的数字为1和10,1出现了两次.
     * 从1到20,含有1的数字为1,10,11,12,13,14,15,16,17,18,19,1出现了11次
     */

    /**
     * 按照传统的方式,我们可以搞两个迭代,第一个迭代负责统计总次数,第二个迭代负责统计该数字与多少个1
     * 这种方式易于理解,但是时间复杂度较大
     *
     * @param maxValue
     * @return
     */
    int statisticsNume_1(int maxValue) {
        if (maxValue <= 0)
            return 0;
        int statisNum = 0;

        for (int i = 1; i <= maxValue; i++) {
            statisNum += get1Nums(i);
        }
        return statisNum;
    }

    int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1)
                res++;
            num = num / 10;
        }
        return res;
    }

    /**
     * 这里我们可以考虑1出现的规律
     *
     * @param maxValue
     * @return
     */
    int statisticsNume_2(int maxValue) {

        //1  10             2
        //11  100
        return 0;
    }


}
