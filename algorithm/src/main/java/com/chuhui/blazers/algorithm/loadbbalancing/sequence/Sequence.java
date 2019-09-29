package com.chuhui.blazers.algorithm.loadbbalancing.sequence;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.INVOKE_NUM;
import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.IP_LIST;

/**
 * Sequence
 * <p>
 * 顺序策略
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class Sequence {


    private Lock lock = new ReentrantLock();

    private AtomicInteger index = new AtomicInteger(0);

    private volatile int newIndex = 0;


    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new CustomerThreadFactory("load balancing sequence-"));


        final Sequence sequence = new Sequence();

        for (int i = 0; i < 5; i++) {

            executor.execute(() -> {

                IntStream.rangeClosed(1, 111).forEach(e ->
                        System.err.println(Thread.currentThread().getName() + "--->" + sequence.getServer()));
            });
        }

        executor.shutdown();
        while (executor.isTerminating()) {

        }

        System.err.println("========================================");
        System.err.println("========================================");

    }


    String getServer() {

        lock.lock();
        try {

            // 多个线程之间切换的时候,会出现问题

            if (index.get() == IP_LIST.size()) {
                index.set(0);
            }
            String ip = IP_LIST.get(index.getAndIncrement());

            return ip;
        } finally {
            lock.unlock();
        }

    }

}
