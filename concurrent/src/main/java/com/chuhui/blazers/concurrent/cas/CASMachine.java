package com.chuhui.blazers.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CASMachine
 *
 * @author: 纯阳子
 * @Date: 2019/6/18
 * @Description:TODO
 */
public class CASMachine {
    public static void main(String[] args) {

        /**
         * CAS是compare And Swap的简称,见名知意,比较并且替换
         *
         * 每个Atomic类,最终调用的是{@link sun.misc.Unsafe}中的方法
         *
         * 每一个涉及{@link sun.misc.Unsafe}类中的方法,都是原子操作,涉及到cpu的系统原语
         *
         *
         * 和synchronized不同的是,synchronized将代码块锁上,不允许其他线程执行
         *
         *
         * 由此暴露出其缺点:
         * 在线程竞争激烈的场景下,
         *
         *https://blog.csdn.net/hxpjava1/article/details/79408692
         *
         */


        AtomicInteger atomicInt=new AtomicInteger(10);
        atomicInt.set(100);
        atomicInt.compareAndSet(100,200);
        atomicInt.incrementAndGet();

    }







}
