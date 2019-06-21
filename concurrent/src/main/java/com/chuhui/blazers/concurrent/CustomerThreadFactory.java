package com.chuhui.blazers.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CustomerThreadFactory
 * 实现{@link ThreadFactory},为每一个线程设置名称
 * @author: 纯阳子
 * @Date: 2019/6/19
 * @Description:TODO
 */
public class CustomerThreadFactory implements ThreadFactory {

    private AtomicInteger atomicInt=new AtomicInteger(0);

    private String customerName;

    public CustomerThreadFactory(String customerName){
        this.customerName=customerName;
    }


    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        thread.setName(String.format(customerName + "%d", atomicInt.incrementAndGet()));
        return thread;
    }
}
