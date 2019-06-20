package com.chuhui.blazers.smallexam;

/**
 * Retry使用
 * 吾辈既务斯业,便当专心用功.
 * 以后名扬四海,根据即在年轻.
 *
 * @author 纯阳子
 * @Desc <a href="blog.csdn.net/u014763302/article/details/54617683"/>
 */
public class RetryDemo {


    public static void main(String[] args) {
        retryCase();
    }


    static void retryCase() {
        int a = 10;
        retry:

        if (a > 0) {
            break retry;
        }



        System.err.println(12343445);

    }


}
