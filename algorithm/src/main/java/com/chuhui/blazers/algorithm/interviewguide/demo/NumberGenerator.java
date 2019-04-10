package com.chuhui.blazers.algorithm.interviewguide.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/3 19:46
 * Description:随机数字产生器
 */
public class NumberGenerator {



    static final int NUMBER_FLAG = 5000_0000;
    static final  int MAX_NUM=10_0000;

    static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        try {

            System.err.println("start execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(new File("D:\\demoFile\\number.txt")), "utf-8");

            ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                service.execute(() -> {
                    Random random = new Random();
                    while (count.get()< NUMBER_FLAG) {

                        try {

                            StringBuffer buffer = new StringBuffer(random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    .append(" "+random.nextInt(MAX_NUM))
                                    ;

                            outputStream.write(buffer.toString()+"\n");

                            count.getAndAdd(20);


//                        System.out.println(i1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
            service.shutdown();
            while (!service.isTerminated()) {

            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("end execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


}
