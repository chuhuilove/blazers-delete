package com.chuhui.blazers.concurrent.threadcommunicate;

import org.junit.Test;

import java.rmi.ServerError;

/**
 * ThreadCommunicate
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/6/24
 * @Description: 线程之间的通信方式
 */
public class ThreadCommunicate {


    /**
     * 两个线程乱序执行
     */

    public void executeInSequence1() {

        Thread threadFirst = new Thread(() -> printData("threadFirst"));
        Thread threadSecond = new Thread(() -> printData("threadSecond"));

        threadFirst.start();
        threadSecond.start();

    }


    /**
     * threadFirst 等待 threadSecond执行完毕后,再执行
     */
    public void executeInSequence2() {

        Thread threadSecond = new Thread(() -> printData("threadSecond"));
        Thread threadFirst = new Thread(() -> {

            System.err.println("threadFirst开始等待threadSecond");
            try {
                threadSecond.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printData("threadFirst");
        });

        threadFirst.start();
        threadSecond.start();
    }




    



    private void printData(String data) {
        int counter = 10;
        while (counter-- > 0) {
            System.err.println(Thread.currentThread().getName() + "--->" + data + counter);
        }
    }

    public static void main(String[] args) {


        ThreadCommunicate threadCommunicate = new ThreadCommunicate();

        threadCommunicate.executeInSequence2();

        try {
            // 主线程等待子线程执行完毕
            // 否则,程序会直接退出
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
