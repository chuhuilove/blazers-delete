package com.chuhui.blazers.commcustome;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread
 *
 * @author: 纯阳子
 * @Date: 2019/7/26 0026
 * @Description: 整个项目使用的线程工厂
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
