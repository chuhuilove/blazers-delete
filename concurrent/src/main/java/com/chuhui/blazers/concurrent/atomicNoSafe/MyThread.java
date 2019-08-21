package com.chuhui.blazers.concurrent.atomicNoSafe;

/**
 * MyThread
 *
 * @author: cyzi
 * @Date: 2019/8/21 0021
 * @Description:TODO
 */
public class MyThread extends Thread {

    private MyService service;

    public MyThread(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {

        System.err.println(Thread.currentThread().getName()+" called...");
        service.addNum();


    }
}
