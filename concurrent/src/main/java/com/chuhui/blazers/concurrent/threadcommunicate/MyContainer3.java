package com.chuhui.blazers.concurrent.threadcommunicate;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 * 使用lock和condition实现
 */
public class MyContainer3<T> {

    final private LinkedList<T> lists = new LinkedList<>();

    /**
     * 最多十个元素
     */
    final private int MAX = 10;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition custome = lock.newCondition();

    private int count = 0;


    public void put(T t) {

        try {
            lock.lock();

            while (lists.size() == MAX) {
                // 如果容器已满,生产者全部等着
                producer.await();
            }

            lists.add(t);
            ++count;
            // 通知消费者线程进程消费
            custome.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public synchronized T get() {
        T t = null;
        try {

            lock.lock();
            while (lists.size() == 0) {
                // 如果容器已空,消费者全部等着
                custome.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();// 通知生产者进程生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;

    }


    public static void main(String[] args) {
        final MyContainer3<String> c = new MyContainer3<>();

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
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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


    }


}
