package com.chuhui.blazers.algorithm.interviewguide.demo.threadpool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/29 21:08
 * Description:TODO
 */
public class ThreadPoolCommunication {
    static final int nThreads = Runtime.getRuntime().availableProcessors();
    final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);


    public static void main(String[] args) {
        new ThreadPoolCommunication().spiderInternetData();
    }

    void spiderInternetData() {
        final AtomicBoolean atomFlag = new AtomicBoolean(true);
        BlockingQueue<String> collections = dataGen();
        IntStream.range(0, nThreads).forEach(e -> executorService.execute(() -> {
                    while (collections.size() > 0) {

                        if (collections.size() == 400) {   //当队列中的元素数量等于400时，模拟出现异常，停掉线程池
                            if (atomFlag.compareAndSet(true, false)) {

                                System.err.println(Thread.currentThread().getName() + "抛出异常。。。。。");
                                //将剩余的url持久化。。。。
                                executorService.shutdownNow();
                            }
                            return;
                        } else {
                            try {
                                System.out.println(Thread.currentThread().getName() + "" + collections.take() + "--->" + collections.size());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                }

        ));
    }

    protected BlockingQueue<String> dataGen() {

        List<String> urls = IntStream.range(0, 30).boxed().map(e -> {
            String format = LocalDate.now().plusDays(e).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return IntStream.rangeClosed(1, 500)
                    .mapToObj(url -> "http://xxx.xxx.xx:9100/getData?city=" + UUID.randomUUID().toString() + "&date=" + format)
                    .collect(Collectors.toList());

        }).flatMap(e -> e.stream()).collect(Collectors.toList());

        BlockingQueue<String> collections = new ArrayBlockingQueue<>(urls.size());

        urls.forEach(url -> {
            try {
                collections.put(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return collections;
    }

}
