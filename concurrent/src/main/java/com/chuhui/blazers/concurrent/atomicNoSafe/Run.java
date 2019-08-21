package com.chuhui.blazers.concurrent.atomicNoSafe;

/**
 * Run
 *
 * @author: cyzi
 * @Date: 2019/8/21 0021
 * @Description:TODO
 */
public class Run {

    /**
     * 原子类也不安全?
     */

    static final int THREAD_MAX_NUM = Runtime.getRuntime().availableProcessors()<<2;

    public static void main(String[] args) throws InterruptedException {

        MyService service = new MyService();

        MyThread[] threadArr = new MyThread[THREAD_MAX_NUM];


        for (int i = 0; i < THREAD_MAX_NUM; i++) {
            threadArr[i] = new MyThread(service);
        }


        for (int i = 0; i < THREAD_MAX_NUM; i++) {
            threadArr[i].start();
        }


        System.err.println("the last output is:" + service.aiRef.get());


    }


}
