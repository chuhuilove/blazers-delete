package com.chuhui.blazers.concurrent.threadcommunicate;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * MyContainer2
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/8/26
 * @Description: 写一个固定容量的同步容器, 拥有put和get方法, 以及getCount()方法
 * 能够支持2个生产者线程以及10个消费者线程的同步调用
 * <p>
 * 使用wait和notify和notifyAll()实现
 */
public class MyContainer2<T> {

    final private LinkedList<T> lists = new LinkedList<>();

    /**
     * 最多十个元素
     */
    final private int MAX = 10;


    private int count = 0;


    public synchronized void put(T t) {

        while (lists.size() == MAX) {
            try {
                //wait 很多情况下都是和while一起使用
                // 虚假唤醒的问题
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        // 通知消费者线程进程消费
        // 必须使用notifyAll,若是使用notify,只会唤醒一个线程,可能是生产者线程,进来后,又锁住了
        this.notifyAll();
    }

    public synchronized T get() {

        while (lists.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t = lists.removeFirst();

        count--;
        notifyAll();// 通知生产者进程生产

        return t;

    }

    public static void main(String[] args) {
        MyContainer3<String> c = new MyContainer3<>();


        /**
         * 启动消费者
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.err.println(c.get());
                }
            }, "c" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 启动生产者
         */
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {

                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }


    }

}
