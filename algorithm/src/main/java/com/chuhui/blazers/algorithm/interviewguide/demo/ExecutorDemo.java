package com.chuhui.blazers.algorithm.interviewguide.demo;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/26 18:39
 * Description:TODO
 */
public class ExecutorDemo {
   static  CopyOnWriteArrayList<Integer> lists=new CopyOnWriteArrayList<>();
   static  int nThreads = Runtime.getRuntime().availableProcessors();
   static ExecutorService executorService= Executors.newFixedThreadPool(nThreads);
    public static void main(String[] args) {




        IntStream.rangeClosed(0,nThreads<<10).forEach(e->lists.add(e));

        ExecutorDemo executorDemo = new ExecutorDemo();

        IntStream.range(0,200).forEach(e->executorDemo.executeTask(2,e+1));

//        executorService.shutdownNow();


        while(!executorService.isTerminated()){
            System.out.println("线程池没有终止");
        }

        System.out.println("--------------------------------------");
        System.out.println("------------线程池已经终止--------------");
        System.out.println("--------------------------------------");

    }


    void executeTask(int num,int seq){
        IntStream.rangeClosed(0,nThreads<<num).forEach(e->
                executorService.execute(()->{
                        System.out.println("第"+seq+"批Thread "+Thread.currentThread().getName()+" remove:"+lists.remove(e));
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
                        }
                )
        );

    }

    public static void main1(String[] args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService= Executors.newFixedThreadPool(nThreads);


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        for(int  i=0;i<Runtime.getRuntime().availableProcessors()*2;i++){
            threadPoolExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
        }
        System.out.println("执行完毕了？？？？？？");


//        executorService.isTerminated()



    }
}
