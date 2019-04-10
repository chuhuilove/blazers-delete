package com.chuhui.blazers.algorithm.interviewguide.chapter5;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/23 22:48
 * Description:判断两个字符串是否互为变形词
 */
public class CheckStrDeformation {


    static boolean customerCheckStrDeformation(String str1, String str2) {

        if (str1 == null || str2 == null || str1.length() != str2.length())
            return false;

        if (str1.equals(str2))
            return true;

        char[] str1Chars = str1.toCharArray(); //字符串中的字符
        char[] str2Chars = str2.toCharArray();


        for (int i = 0; i < str1Chars.length; i++) {

            int str1Sum = 1;  //str1中某个字符的数量
            int str2Sum = 1;  //str2中某个字符的数量
            char oneChars = str1Chars[i];


            for (int j = 0; j < str1Chars.length; j++)
                if (str1Chars[j] == oneChars) {
                    str1Sum += 1;
                }

            boolean str2Flag = false;

            for (int j = 0; j < str2Chars.length; j++) {
                if (oneChars == str2Chars[j]) {
                    str2Flag = true;
                    str2Sum += 1;
                }
            }

            if (!(str2Flag && str1Sum == str2Sum)) //如果每种字符的数量不一致，则直接返回
                return false;
        }

        return true;
    }


    /**
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length())
            return false;

        char[] str1Chars = str1.toCharArray(); //字符串中的字符
        char[] str2Chars = str2.toCharArray();

        int[] map = new int[256];


        for (int i = 0; i < str1Chars.length; i++) {
            map[str1Chars[i]]++;
        }

        for (int i = 0; i < str2Chars.length; i++) {
            if (map[str2Chars[i]]-- == 0) {  //先比较，再减一。。。。
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        System.err.println(customerCheckStrDeformation("123451asdfghjkl234567123456789089067890", "90876511234lkjhgfdsa5678902345678904321"));


        System.err.println(isDeformation("123451asdfghjkl234567123456789089067890","90876511234lkjhgfdsa5678902345678904321"));


        int i=0;
        if(--i==0){
            System.err.println("这里返回true");
        }

    }

}
