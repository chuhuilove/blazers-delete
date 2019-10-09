package com.chuhui.blazers.algorithm.leetcode.string;

/**
 * Solution
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class SolutionString {


    /**
     * 415 两个字符串相加
     *
     * 解题思路:
     * 两个字符串倒着相加,有进位处理进位
     * 最后将结果字符串翻转
     *
     * @param num1
     * @param num2
     * @return
     */
      static String addStrings(String num1, String num2) {

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
     * 43  字符串相乘
     *
     * @param num1 非负整数字符串1
     * @param num2 非负整数字符串2
     * @return 两个字符串相乘的结果
     */
    public String multiply(String num1, String num2) {

        final String zero="0";

        if(zero.equals(num1)||zero.equals(num2)){
            return zero;
        }




          return null;
    }
}
