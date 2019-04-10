package com.chuhui.blazers.concurrent.deadlylock;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/3/1 16:55
 * Description: 锁顺序死锁
 */
public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();


    private Long sumCount = 0L;
    private Long avgCount;

    private Integer invokeCount = 0;

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                executeSumCountCompute();
            }
        }
    }

    /**
     * 产生死锁的原因:
     * 两个线程以不同的顺序来获取相同的锁
     */
    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                executeAvgCountCompute();
            }
        }
    }

    /**
     * 每一个锁以相同的顺序调用,就不会发生死锁
     */
    public void rightLeftFigure() {
        synchronized (left) {
            synchronized (right) {
                executeAvgCountCompute();
            }
        }
    }

    public Long getSumCount() {
        return sumCount;
    }


    public Long getAvgCount() {
        return avgCount;
    }


    void executeSumCountCompute() {
        IntStream.rangeClosed(1, new Random().nextInt()).forEach(e -> sumCount++);
        invokeCount++;
    }

    void executeAvgCountCompute() {
        avgCount = sumCount / invokeCount;
        System.err.println(Thread.currentThread().getName() + "计算平均值:" + avgCount);
    }

    public static void main(String[] args) {

        LeftRightDeadlock deadlock = new LeftRightDeadlock();

        List<Thread> threads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors()).mapToObj(e ->
                new Thread(() -> {
                    deadlock.leftRight();
                    deadlock.rightLeft();
                })
        ).collect(Collectors.toList());

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.err.println("最终的avg值:" + deadlock.getAvgCount());
        System.err.println("最终的sum值:" + deadlock.getSumCount());
    }
}



