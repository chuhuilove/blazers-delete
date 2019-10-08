package com.chuhui.blazers.concurrent.threadpool;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * 用心研究每一个知识点
 *
 * @Author: 纯阳子
 * @Date: 2019/6/20 09:53:03
 * @Description: README.md
 */
public class ThreadPoolExample {

    ExecutorService executor;

    final int MAX_TASK_COUNT = 100;
//    final int MAX_TASK_COUNT = Runtime.getRuntime().availableProcessors() << 4;

    final List<String> uuids = new CopyOnWriteArrayList<>();

    /**
     * 单个整个线程池中只有一个线程
     */
    void signThreadPool() {

        executor = new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new CustomerThreadFactory("SingleThreadExecutor-"), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= MAX_TASK_COUNT; i++) {
            final int a = i;
            executor.execute(() -> qryData("task-" + a));
        }

    }

    void closeThreadPool() {
        if (executor != null) {
            executor.shutdown();
        }
    }


    void qryData(String taskName) {

        int counter = 1;
        while (counter++ < 10000) {
            String uuid = UUID.randomUUID().toString();
            uuids.add(Thread.currentThread().getName() + "---->" + uuid);
            if (counter > 9999) {
                System.err.println(Thread.currentThread().getName() + " 已经循环" + counter + "次,结束."+taskName);
            }
        }

    }


    public static void main(String[] args) {

        //线程池的工作原理
        //里面到底采用了什么东西呢


        ThreadPoolExample threadPool = new ThreadPoolExample();
        threadPool.signThreadPool();
//        threadPool.closeThreadPool();

        /**
         * 任务队列和线程池不是耦合的,那么当线程池中的任务完事以后.
         * 是通过什么机制从队列中获取任务的?
         */



    }


}
