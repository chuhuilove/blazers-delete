package com.chuhui.blazers.algorithm.interviewguide.chapter7;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/3 19:59
 * Description: 利用位运算实现加减乘除
 */
public class BasicOperation {

    public int add(int a,int b){
        int sum=a;
        while (b!=0){
            sum=a^b;
            b=(a&b)<<1;
            a=sum;
        }
        return sum;
    }

}
