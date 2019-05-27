package com.chuhui.blazers.socket.nio.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * TimeClient
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description:TODO
 */
public class TimeClient {


    static final ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("client:%d").build();

    static final ExecutorService executorService = new ThreadPoolExecutor(1, 1, 2000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), factory);

    public static void main(String[] args) {
        executorService.execute(new TimeClientHandle("127.0.0.1", 8001));

    }

}
