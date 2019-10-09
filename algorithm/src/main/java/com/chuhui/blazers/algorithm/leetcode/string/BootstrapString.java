package com.chuhui.blazers.algorithm.leetcode.string;

import static com.chuhui.blazers.algorithm.leetcode.string.SolutionString.addStrings;

/**
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class BootstrapString {

    public static void main(String[] args) {

        function415();
    }


    static void function415(){

        String str1="12321323213237567384481671236128362183248643641231372638713627361784218";
        String str2="45532432434129878971928721312798316439846189468471232736856892147219837213982173892733728931";
        System.err.println(addStrings(str1,str2));
    }

}
