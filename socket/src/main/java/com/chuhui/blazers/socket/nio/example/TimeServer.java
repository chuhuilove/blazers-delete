package com.chuhui.blazers.socket.nio.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * example
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description:TODO
 */
public class TimeServer {

    static final ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("server:%d").build();

    static final ExecutorService executorServce = new ThreadPoolExecutor(1, 1, 2000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), factory);


    public static void main(String[] args) {




        MultiplexerTimeServer server = new MultiplexerTimeServer(8001);
        executorServce.execute(server);
    }


}
