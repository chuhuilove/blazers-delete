package com.chuhui.blazers.concurrent.threadcommunicate;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * MyCa
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/8/26
 * @Description: 摒弃能跑就行的思想
 */
public class MyContainer<T> {

    private List<T> lists = new ArrayList<>();


    public void add(T t) {
        lists.add(t);
    }

    public int size() {
        return lists.size();
    }

    /**
     * wait 和 notify
     * wait 会释放锁,notify不会释放锁
     * <p>
     * 所以需要先调用wait,再调用notify.
     * <p>
     * 这个程序有缺陷,只能等待t1执行完毕以后,t2才能结束
     *
     * @param args
     */
    public static void mainWaitNotify(String[] args) {

        MyContainer<Integer> container = new MyContainer<>();

        final Object lock = new Object();


        new Thread(() -> {


            synchronized (lock) {
                if (container.size() != 5) {

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.err.println("t2结束");
        }).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.err.println("t1启动");

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container.add(i);
                    if (container.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();


    }


    public static void mainWaitNotifyDouble(String[] args) {

        MyContainer<Integer> container = new MyContainer<>();

        final Object lock = new Object();


        new Thread(() -> {


            synchronized (lock) {
                if (container.size() != 5) {

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.err.println("t2结束");

            lock.notify();
        }).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.err.println("t1启动");

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container.add(i);
                    if (container.size() == 5) {
                        lock.notify();


                        try {
                            // 释放锁,让t2继续执行
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();
    }

    public static void main(String[] args) {


        MyContainer<Integer> container = new MyContainer<>();

        final CountDownLatch latch = new CountDownLatch(1);


        new Thread(() -> {
            System.err.println("t2开始");
            if (container.size() != 5) {

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("t2结束");

        }).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.err.println("t1启动");


            for (int i = 0; i < 10; i++) {
                container.add(i);
                if (container.size() == 5) {
                    latch.countDown();

                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


    }


}
