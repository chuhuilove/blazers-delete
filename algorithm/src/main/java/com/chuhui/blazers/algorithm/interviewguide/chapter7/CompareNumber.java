package com.chuhui.blazers.algorithm.interviewguide.chapter7;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/3 20:15
 * Description:不用任何判断找出两个数中的较大的数
 */
public class CompareNumber {


    public static void main(String[] args) {

        int a=4<<10;
        int b=4<<9;

        System.out.println(new CompareNumber().getMax1(a,b));
    }

    public  int getMax1(int a,int b){

        int c=a-b;
        int scA=sign(c);
        int scB=flip(scA);


        return a*scA+b*scB;
    }

    public int getMax2(int a,int b){
        int c=a-b;
        int sa=sign(a);
        int sb=sign(b);
        int sc=sign(c);
        int difSab=sa^sb;
        int sameSab=flip(difSab);
        int returnA=difSab*sa+sameSab*sc;
        int returnB=flip(returnA);
        return a*returnA+b*returnB;
    }




    public  int flip(int n){
        return n^1;
    }

    public  int sign(int n){
        return flip((n>>31)&1);
    }





}
