package com.chuhui.blazers.smallexample.example;

/**
 * Retry使用
 * 吾辈既务斯业,便当专心用功.
 * 以后名扬四海,根据即在年轻.
 *
 * @author 纯阳子
 * @Desc <a href="https://blog.csdn.net/zhizhuodewo6/article/details/77368819"/>
 */
public class RetryDemo {


    public static void main(String[] args) {
//        retryCase();
        demo();
    }


    static void retryCase() {
        int a = 10;
        retry:

        if (a > 0) {
            break retry;
        }


        System.err.println(12343445);

    }


    public static void testRequest() {
        retry:
// 1（行2）
        for (int i = 0; i < 10; i++) {
//            retry:
// 2（行4）
            while (i == 5) {
                continue retry;
            }
            System.out.print(i + " ");
        }
    }


    private static void demo() {
        outer:
        while (true) {
            go:
            for (int i = 0; i < 10; i++) {
                System.err.println("i值为：" + i);

                if (i == 2) {
                    inter:
                    for (int j = 0; i < 3; j++) {

                        System.err.println("j值为：" + j);
                        if (j == 1) {
                            System.err.println("j==1跳出inter");

                            break inter;
                        }
                    }
                }
                if (i == 3) {
                    System.err.println("i==3，继续循环go");
                    continue go;
                }
                if (i == 4) {
                    System.err.println("i==4，跳出outer");

                    break outer;
                }
            }
        }


    }
}
