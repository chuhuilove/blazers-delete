package com.chuhui.blazers.concurrent.atomicNoSafe;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    static final int THREAD_MAX_NUM = Runtime.getRuntime().availableProcessors() << 2;

    public static void main(String[] args) throws InterruptedException {

        final MyService service = new MyService();


        // 创建一个线程池
        // 核心只有1个,最大有1000
        // 使用的是有界队列
        // 当第二个任务进来以后,

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1000, 1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2), new CustomerThreadFactory("atomicNoSafe:"));


        for (int i = 0; i < THREAD_MAX_NUM; i++) {
            executor.execute(() -> {
                service.addNum();
            });
        }

        executor.shutdown();


        while (!executor.isTerminated()){

        }




//
//        MyThread[] threadArr = new MyThread[THREAD_MAX_NUM];
//
//
//        for (int i = 0; i < THREAD_MAX_NUM; i++) {
//            threadArr[i] = new MyThread(service);
//        }
//
//
//        for (int i = 0; i < THREAD_MAX_NUM; i++) {
//            threadArr[i].start();
//        }
//
//
        System.err.println("the last output is:" + MyService.aiRef.get());


    }


}
