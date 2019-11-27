package com.chuhui.blazers.concurrent.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * FutureExample
 *
 * @author: 纯阳子
 * @Date: 2019/7/23 0023
 * @Description:TODO
 */
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.err.println(Thread.currentThread().getName() + " start execut main: " + currentTime());
        FutureTask<Integer> firstTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                System.err.println(Thread.currentThread().getName() + " execute firstTask " + currentTime());
                TimeUnit.SECONDS.sleep(10);
                return 10;
            }
        });

        FutureTask<Integer> secondTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.err.println(Thread.currentThread().getName() + " execute secondTask " + currentTime());

                TimeUnit.SECONDS.sleep(5);
                return 15;
            }
        });

        Thread firstThread = new Thread(firstTask);
        Thread secondThread = new Thread(secondTask);
        System.err.println(Thread.currentThread().getName() + " execute start thread " + currentTime());

        firstThread.start();
        secondThread.start();

        System.err.println(Thread.currentThread().getName() + " wait result " + currentTime());

        Integer secondInt = secondTask.get();
        Integer firstInt = firstTask.get();

        System.err.println(Thread.currentThread().getName() + " compute result " + (firstInt + secondInt)+" "+currentTime());

    }

    static String currentTime() {
        return returnCurrentTimeFormated(commonlyUserDateTimeFormat);
    }

}
