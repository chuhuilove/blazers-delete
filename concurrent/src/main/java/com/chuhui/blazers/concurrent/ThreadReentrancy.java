package com.chuhui.blazers.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/28 17:46
 * Description: synchronized 可重入
 */
public class ThreadReentrancy {


    /**
     * 当某个线程请求一个由其他线程持有的锁时，发出请求的线程就会阻塞。然而，由于内置锁的可重入性，
     * 因此如果某个线程试图获取已经由它自己持有的锁，那么这个请求就会成功。“重入”意味着获取锁的操作粒度是“线程”而不是“调用”。
     * 重入的一种实现方法是，为每一锁关联一个获取计数器值和一个所有者线程。当计数器为0时，这个锁就被认为是没有被任何线程持有。
     * 当线程请求一个未被持有的锁时，JVM将记下锁的持有者，并且将获取计数器置为1.如果同一个线程再次获取这个锁，计数器值将递增，
     * 而当线程退出同步代码块时，计数器会响应地递减，。当计数值为0时，这个锁将被释放。
     * 《java并发编程实战 2.3.2 重入》
     *
     */



    public static class DemoServlet1 {
        static int i=0;
        static int j=0;

       static Lock lock = new ReentrantLock();

        public void doSomething() {

            lock.lock();
            try {
                i++;
                System.err.println("一个线程进来了...." + Thread.currentThread().getName() + "...DemoServlet1.doSomething i="+i);
                interFun("内部调用");
            } finally {
                lock.unlock();
            }
        }

        void interFun(String str) {
            lock.lock();
            try {

                j++;
                System.err.println(str + "一个线程进来了..." + Thread.currentThread().getName() + "....DemoServlet1.interFun  j="+j);
            } finally {
                lock.unlock();
            }
        }

    }

    public static class DemoServlet2 {

        synchronized void doSomething() {
            System.err.println("一个线程进来了...." + Thread.currentThread().getName() + "...DemoServlet2.doSomething");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            interFun("内部调用");
        }

        synchronized void interFun(String str) {
            System.err.println(str + "一个线程进来了..." + Thread.currentThread().getName() + "....DemoServlet2.interFun");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        DemoServlet1 servlet2 = new DemoServlet1();

        IntStream.rangeClosed(1, 9).forEach(e ->
                new Thread(() -> {
                    servlet2.doSomething();
                    servlet2.interFun("直接调用");
                }).start());


    }


}
