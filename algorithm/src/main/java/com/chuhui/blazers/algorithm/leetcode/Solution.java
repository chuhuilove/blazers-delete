package com.chuhui.blazers.algorithm.leetcode;

import java.util.*;

/**
 * Created by Administrator on 2019/5/10 0010.
 */
public class Solution {

    public static void main(String[] args) {

//        customerStr("zyxwvutsrqponmlkjihgfedcba", null);

//        System.err.println(findDuplicate(new int[]{1,3,2,3,4,5,3,6,7,8,9}));


        int a=10;
        int b=10;
        int c=-11;
        System.err.println(a^b^c);

    }


    public static String customerStr_example(String S, String T) {


        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder(T);


        return null;
    }


//    给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

    /**
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(final int[] nums) {


        for (int i = 0; i < nums.length; i++) {


        }
//判断环状态链表

        return 0;
    }


    public static int singleNumber(int[] nums) {

        for(int i=0;i<nums.length;i++){


        }


        return 0;
    }

    /**
     * @param S abcdefg
     * @param T
     * @return
     */
    public static String customerStr(String S, String T) {

        //保证S的序列
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), i);
        }


        //分配一个和T长度相同的数组

        List<Character> charList = new LinkedList<>();


        List<Character> noExist = new ArrayList<>();


        Character[] tempStr = new Character[T.length()];

        int arrayFlag = 0;

        for (int i = 0; i < T.length(); i++) {

            char currentChar = T.charAt(i);

            //如果S串中不包含该字符
            if (!map.containsKey(currentChar)) {
                noExist.add(currentChar);
                continue;
            }

            Integer integer = map.get(currentChar);


            if (tempStr[integer] != null) {
                //自 interger 全部向后移动一个

                //判断一下,向前移动,还是向后移动

                for (int j = 0; j < tempStr.length; j++) {

                    if (tempStr[j] == currentChar) {
                        //向前搜索位置
                        //向后搜索位置

                        boolean isSuccess = false;

                        for (int h = j - 1; h >= 0; h--) {
                            if (tempStr[h] == null && tempStr[h + 1] == currentChar) {
                                tempStr[h] = currentChar;
                                isSuccess = true;
                                break;
                            }
                        }

                        if (!isSuccess) {
                            for (int h = j + 1; h < tempStr.length; h++) {
                                if (tempStr[h] == null && tempStr[h - 1] == currentChar) {
                                    tempStr[h] = currentChar;
                                    isSuccess = true;
                                    break;
                                }
                            }
                        }

                        if (!isSuccess) {

                        }

                    }

                }

            } else {
                tempStr[integer] = currentChar;
            }


//            for(int j=0;j<tempStr.length;j++){
//
//                if()
//
//
//            }


//            if(charList.size()<=0){
//                charList.add(currentChar);
//                continue;
//            }
            //当前字符的顺序


//            Character character = charList.get(integer);
//            if(character==null){
//                charList.add(integer,currentChar);
//            }else{
//                //向后移动一个位置
//                for(int j=integer;j<charList.size())
//            }


        }


        return null;

    }


}
