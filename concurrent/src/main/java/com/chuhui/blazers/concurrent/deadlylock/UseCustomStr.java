package com.chuhui.blazers.concurrent.deadlylock;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * UseCustomStr
 * <p>
 * 使用字符串作为锁出现的无限等待问题
 *
 * @author: 纯阳子
 * @date: 2019/10/7
 */
public class UseCustomStr implements Runnable {
    private String lockObject = "cyzi";

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new CustomerThreadFactory("UseCustomStr-"));
        executor.execute(new OtherJarThread());
        executor.execute(new UseCustomStr());
    }

    @Override
    public void run() {
        synchronized (lockObject) {
            long j = 0L;
            while (j++ < Long.MAX_VALUE) {
                System.err.println(Thread.currentThread().getName() + "--->" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));
            }
        }
    }
}
