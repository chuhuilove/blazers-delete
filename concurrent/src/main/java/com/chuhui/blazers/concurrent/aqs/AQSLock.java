package com.chuhui.blazers.concurrent.aqs;

import java.rmi.ServerError;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * AQSLock
 *
 * @author: 纯阳子
 * @Date: 2019/6/18
 * @Description:TODO
 */
public class AQSLock {

    Lock lock = new ReentrantLock();

    private volatile int count = 0;

    ThreadPoolExecutor executor = null;


    public static void main(String[] args) {

        ThreadFactory factory = new ThreadFactory() {

            AtomicInteger atomicInt = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {

                Thread thread = new Thread(r);
                thread.setName(String.format("AQSLock threadName%d", atomicInt.incrementAndGet()));

                return thread;
            }
        };

        AQSLock aqsLock = new AQSLock();

        aqsLock.executor = new ThreadPoolExecutor(30, 100,
                2000L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100), factory);

        IntStream.rangeClosed(1, 20).forEach(e -> aqsLock.executor.execute(() -> aqsLock.lockAndInvoke()));


    }


    void lockAndInvoke() {


        /**
         * 每一个线程被封装成一个Node,这些Node会被组建成一个队列,
         *
         */
        String currentThreadName = Thread.currentThread().getName();
        System.err.println(currentThreadName + " 排队中");
        lock.lock();

        //查找一下,是怎么入队的

        System.err.println(currentThreadName + " 获取到锁了");
        try {

            int i = 100;
            while (i-- > 0) {
                Thread.sleep(100L);
                if (i == 99) {
                    System.err.println("当前线程正在运行线程:" + currentThreadName);
                }


                // 如果当前线程是tableName4,则再添加一个线程
                if (currentThreadName.split(" ")[1].equals("threadName4") && i == 99) {

                    executor.execute(() -> lockAndInvoke());
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}
