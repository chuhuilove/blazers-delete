package com.chuhui.blazers.concurrent.threadcommunicate;

import java.util.concurrent.CountDownLatch;

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

    private static void printData(String data) {
        int counter = 10;
        while (counter-- > 0) {
            System.err.println(Thread.currentThread().getName() + "--->" + data + counter);
        }
    }

    /**
     * 还需要恶补线程基础知识
     */
    public void runDAfterABC() {

        final CountDownLatch downLatch = new CountDownLatch(2);


        Thread aThread = new Thread(() -> {
            printData("print A");
//            downLatch.countDown();
        });
        Thread bThread = new Thread(() -> {
            printData("print B");
//            downLatch.countDown();
        });
        Thread cThread = new Thread(() -> {
            printData("print C");
//            downLatch.countDown();
        });
        Thread dThread = new Thread(() -> {
            printData("print D");
//            try {
//                downLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            printData("print D 最终调用");
        });

        Thread eThread = new Thread(() -> {
            printData("print E");
//            try {
//                downLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            printData("print E 最终调用");
        });
        aThread.start();
        bThread.start();
        cThread.start();
        dThread.start();
        eThread.start();
    }


    public static void main(String[] args) {


        ThreadCommunicate threadCommunicate = new ThreadCommunicate();

        threadCommunicate.runDAfterABC();

   /*     try {
            // 主线程等待子线程执行完毕
            // 否则,程序会直接退出
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

    }


}
