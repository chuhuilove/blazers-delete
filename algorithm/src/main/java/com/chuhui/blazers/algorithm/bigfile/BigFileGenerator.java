package com.chuhui.blazers.algorithm.bigfile;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BigFileGenerator
 * <p>
 * 产生大文件
 *
 * @author: 纯阳子
 * @date: 2019/11/11
 */
public class BigFileGenerator {

    static final int MAX_THREAD_COUNT = 10;

    static int MAX_SIZE = Integer.MAX_VALUE;
    volatile static long current = 0;

    static Object obj = new Object();


    public static void main(String[] args) throws IOException {
        final File urlFile1 = new File("url1.txt");
        if (!urlFile1.exists()) {
            urlFile1.createNewFile();
        }
        final File urlFile2 = new File("url2.txt");
        if (!urlFile2.exists()) {
            urlFile2.createNewFile();
        }


        final BufferedWriter writer1 = new BufferedWriter(new FileWriter(urlFile1));
        final BufferedWriter writer2 = new BufferedWriter(new FileWriter(urlFile2));

        String uniqueStr = UUID.randomUUID().toString();
        writer1.write(uniqueStr);
        writer1.newLine();
        writer2.write(uniqueStr);
        writer2.newLine();


        ThreadPoolExecutor executor = new ThreadPoolExecutor(MAX_THREAD_COUNT, MAX_THREAD_COUNT, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(MAX_THREAD_COUNT), new CustomerThreadFactory("file-generator-"));


        for (int i = 1; i <= MAX_THREAD_COUNT; i++) {

            executor.execute(() -> {
                synchronized (obj) {
                    while (current++ < MAX_SIZE) {
                        try {
                            writer1.write(UUID.randomUUID().toString());
                            writer1.newLine();
                            writer2.write(UUID.randomUUID().toString());
                            writer2.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()){

        }
        System.err.println("success....");

    }


}
