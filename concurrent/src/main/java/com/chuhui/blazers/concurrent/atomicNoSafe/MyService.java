package com.chuhui.blazers.concurrent.atomicNoSafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * MyService
 *
 * @author: cyzi
 * @Date: 2019/8/21 0021
 * @Description:TODO
 */
public class MyService {

    public static AtomicLong aiRef = new AtomicLong();

    public void addNum() {
        System.err.println(Thread.currentThread().getName() + " 加了100之后的值是:" + aiRef.addAndGet(100));
        aiRef.addAndGet(1);
    }

}
