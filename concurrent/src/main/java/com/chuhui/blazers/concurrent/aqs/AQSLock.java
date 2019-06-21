package com.chuhui.blazers.concurrent.aqs;

import com.chuhui.blazers.concurrent.CustomerThreadFactory;

import java.rmi.ServerError;
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



    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(30, 30,
                2000L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100), new CustomerThreadFactory());
        AQSLock aqsLock = new AQSLock();
        IntStream.rangeClosed(1, 20).forEach(e -> executor.execute(() -> aqsLock.lockAndInvoke()));


        System.err.println("之后:" + aqsLock.count);
        System.err.println("");
        while (executor.isTerminated()) {
        }
        System.err.println("之后:" + aqsLock.count);
    }


    void lockAndInvoke() {

        /**
         * 每一个线程被封装成一个Node,这些Node会被组建成一个队列,
         *
         */
        lock.lock();
        try {

            int i = 100;
            while (i-- > 0) {
                Thread.sleep(2000L);
                System.err.println("当前线程:" + Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}
