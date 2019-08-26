package com.chuhui.blazers.algorithm.leetcode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * BigNumCompute
 * <p>
 *
 * @author: 纯阳子
 * @Date: 2019/8/20
 * @Description: 大数计算
 */
public class BigNumCompute {


    public static void main(String[] args) {


        /**
         * 从 BigDecimal 中抽取出来一些算法
         */

        BigDecimal bigDecimal = new BigDecimal("112345676123131231323123213213131.112345676123131231323123213213131");


        BigDecimal bigDecimal1 = new BigDecimal("345663234566321212134566321212112121345663212121.345663234566321212134566321212112121345663212121");


        int t_123 = 123;
        int t_4 = 4;
        System.err.println(t_123 / t_4);


    }


    /**
     * 力扣415: 两个字符串相加
     * <p>
     * 自定义
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {

        int num1Length = num1.length() - 1;
        int num2Length = num2.length() - 1;

        int carry = 0;
        StringBuilder builder = new StringBuilder();

        for (; ; ) {

            int compute = 0;

            if (num1Length >= 0) {
                compute = compute + Character.digit(num1.charAt(num1Length--), 10);
            }
            if (num2Length >= 0) {
                compute = compute + Character.digit(num2.charAt(num2Length--), 10);
            }
            compute += carry;

            if (compute >= 10) {
                builder.append(compute % 10);
                carry = 1;
            } else {
                builder.append(compute);
                carry = 0;
            }

            if (num2Length < 0 && num1Length < 0) {
                break;
            }
        }
        if (carry == 1) {
            builder.append(carry);
        }

        return builder.reverse().toString();
    }

    /**
     * 字符串相乘
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {





        for(int i=0;i<num1.length();i++){








        }





        return null;
    }


    public static String subtract(String num1, String num2) {


        return null;
    }





}
