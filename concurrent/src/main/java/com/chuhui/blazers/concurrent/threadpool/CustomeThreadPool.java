package com.chuhui.blazers.concurrent.threadpool;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * CustomeThreadPool
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/8/26
 * @Description: 自定义线程池
 */
public class CustomeThreadPool {


    // 如果自定义一个线程池,需要什么

    //1. 需要一个仓库

    private BlockingQueue<Runnable> blockingQueue;

    //2. 需要一个存放线程的集合

    private List<Thread> works;

    //3. 需要一个工人来干活
    public static class Worker extends Thread {

        private CustomeThreadPool pool;

        public Worker(CustomeThreadPool pool) {
            this.pool = pool;
        }


        @Override
        public void run() {

            //去仓库中拿东西
            while (pool.isWorking || pool.blockingQueue.size() > 0) {
                Runnable task = null;
                try {
                    if (pool.isWorking) {
                        task = pool.blockingQueue.take();
                    } else {
                        task = pool.blockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (task != null) {
                    task.run();

                    System.err.println("线程:" + Thread.currentThread().getName() + " 执行完毕");

                }
            }
        }
    }


    //4. 需要初始化仓库和线程集合

    public CustomeThreadPool(int poolSize, int taskSize) {
        this.blockingQueue = new LinkedBlockingDeque<>(taskSize);
        works = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            works.add(worker);
        }

    }


    //5. 需要向我们仓库放任务的方法,不阻塞,返回一个特殊值

    public boolean submit(Runnable task) {

        if (isWorking) {
            return this.blockingQueue.offer(task);

        }

        return false;
    }


    //6. 需要向我们仓库放任务的方法,阻塞

    public void execute(Runnable task) {
        try {
            blockingQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //7. 关闭线程池的方法
    //a. 关闭的时候,仓库要停止有新的线程进来
    //b. 关闭的时候,仓库如果还有东西,则需要执行完
    //c. 关闭的时候,如果去仓库拿东西,则不能阻塞了, Worker中的run里面,调用的 blockingQueue.take(),是一个阻塞方法
    //d. 关闭的时候,需要把阻塞的线程中断

    private volatile boolean isWorking = true;

    public void shutDown() {
        isWorking = false;

        // 中断掉所有线程
        for (Thread thread : works) {
            if (thread.getState().equals(Thread.State.BLOCKED) || thread.getState().equals(Thread.State.WAITING)) {
                thread.interrupt();
            }
        }
    }
}
