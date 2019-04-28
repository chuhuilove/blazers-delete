package com.chuhui.blazers.algorithm.interviewguide.demo.threadpool;


import com.chuhui.blazers.algorithm.interviewguide.demo.threadpool.model.ThreadExampleModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/28 18:51
 * Description:线程池 生产者 消费者模型
 */
public class ProducerCustomerDemo {
    static final int nThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        new ProducerCustomerDemo().downloadData();
//        new ProducerCustomerDemo().downloadDataSinglePool();
    }





    void downloadDataSinglePool() {

        ExecutorService customerService = Executors.newFixedThreadPool(nThreads);
        final Random random = new Random();

        IntStream.range(0, nThreads << 20).forEach(e -> customerService.execute(() -> {
            int nextInt = random.nextInt(10000) + 10;

            if (nextInt == 20 || nextInt == 21 || nextInt == 22 || nextInt == 23 || nextInt == 24) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
                throw new NullPointerException("一个异常出现了");
            }else {



            }

        }));
        customerService.shutdown();
        while (!customerService.isTerminated()) {

        }

        System.err.println("线程池已经关闭");

    }

    void downloadData() {

        ExecutorService customerService = Executors.newFixedThreadPool(nThreads);
        ExecutorService producerService = Executors.newFixedThreadPool(nThreads);

        BlockingQueue<ThreadExampleModel> collections = new ArrayBlockingQueue<>(2000);

        IntStream.rangeClosed(0, nThreads).forEach(e -> customerService.execute(new CustomerService(collections)));
        IntStream.rangeClosed(0, nThreads << 15).forEach(e -> producerService.execute(new ProducerService(e, 20, collections)));

        producerService.shutdown();
        customerService.shutdown();

        while (true) {
            if (producerService.isTerminated() && customerService.isTerminated()) {

                System.err.println("完事");
                System.err.println("完事");
                break;
            }
        }

    }


    public static class CustomerThreadFactory implements ThreadFactory {

        private Object obj = new Object();

        private ThreadFactory defaultFactory;

        public static CustomerThreadFactory factory;

        private CustomerThreadFactory() {
            this.defaultFactory = Executors.defaultThreadFactory();
        }

        public static CustomerThreadFactory getInstance() {
            if (factory == null) {
                factory = new CustomerThreadFactory();
            }
            return factory;
        }


        @Override
        public Thread newThread(Runnable r) {

            Thread thread = defaultFactory.newThread(r);

//            thread.setUncaughtExceptionHandler((t, e) -> System.out.println("出现异常了。。。。。。。。。。。。。"));


//            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                @Override
//                public void uncaughtException(Thread t, Throwable e) {
//                    System.out.println("出现异常了。。。。。。。。。。。。。");
//                }
//            });

            return thread;
        }
    }

    private class ProducerService implements Runnable {
        private BlockingQueue<ThreadExampleModel> collections;
        private int pageIndex;
        private int pageSize;

        public ProducerService(int pageIndex, int pageSize, BlockingQueue<ThreadExampleModel> collections) {
            this.collections = collections;
            this.pageIndex = pageIndex;
            this.pageSize = pageSize;
        }

        @Override
        public void run() {

            IntStream.rangeClosed(1, pageSize).forEach(e -> {
                try {
                    collections.put(new ThreadExampleModel(pageIndex));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });

        }
    }

    private class CustomerService implements Runnable {

        private BlockingQueue<ThreadExampleModel> collections;

        public CustomerService(BlockingQueue<ThreadExampleModel> collections) {
            this.collections = collections;
        }

        volatile boolean run = true;

        @Override
        public void run() {
            try {
                while (run) {  //批量获取,适用于消费者速率小于生产者速率的场景
                    Thread.sleep(2000);
                    List<ThreadExampleModel> models = new ArrayList<>();
                    if (collections.drainTo(models) <= 0)
                        run = false;
                    else {
                        System.err.println("从队列中获取" + models.size() + "条数据");
                        executeSave(models);
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }

    void executeSave(List<ThreadExampleModel> models) {
        models.forEach(e -> {
            if (e.getAge() == 20 || e.getAge() == 21 || e.getAge() == 22 || e.getAge() == 23 || e.getAge() == 24) {
                throw new NullPointerException("一个异常出现了");
            }
        });
    }
}
