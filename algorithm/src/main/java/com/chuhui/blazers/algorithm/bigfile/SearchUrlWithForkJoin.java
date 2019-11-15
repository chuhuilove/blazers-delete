package com.chuhui.blazers.algorithm.bigfile;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static com.chuhui.blazers.algorithm.bigfile.SearchUrl.writeToFile;
import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * SearchUrlWithForkJoin
 *
 * @author: cyzi
 * @Date: 2019/11/12 0012
 * @Description:TODO
 */
public class SearchUrlWithForkJoin {


    public static void main(String[] args) {


//        final BufferedReader read1 = new BufferedReader(new FileReader(new File("url1.txt")));
//        final BufferedReader read2 = new BufferedReader(new FileReader(new File("url2.txt")));
//
//        BufferedReader bufferedReader = Files.newBufferedReader(FileSystems.getDefault().getPath("url1.txt"));


//        Files.lines(FileSystems.getDefault().getPath("url1.txt"))
//                .parallel().forEach(e -> {
//
//            System.err.println(Thread.currentThread().getName());
//        });


        final Map<Integer, BufferedWriter> writeMap = new ConcurrentHashMap<>(2);
        System.err.println("start:" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));

        try {
            Files.lines(FileSystems.getDefault().getPath("url1.txt"))
                    .parallel().forEach(e -> {
                try {
                    writeToFile(writeMap, e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.lines(FileSystems.getDefault().getPath("url2.txt"))
                    .parallel().forEach(e -> {
                try {
                    writeToFile(writeMap, e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("hash分流完成:" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));

    }


    static void startForkJoin() {
        CustomzedRecursiveAction action = new CustomzedRecursiveAction(1, 90000000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(action);

        try {
            System.err.println("final value:" + action.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    static class CustomzedRecursiveAction extends RecursiveTask<Integer> {


        private int beginVal;
        private int endVal;

        CustomzedRecursiveAction(int beginVal, int endVal) {
            this.beginVal = beginVal;
            this.endVal = endVal;
        }


        @Override
        protected Integer compute() {

            if ((endVal - beginVal) != 0) {
                int middleNum = (beginVal + endVal) / 2;
                CustomzedRecursiveAction leftAction = new CustomzedRecursiveAction(beginVal, middleNum);
                CustomzedRecursiveAction rightAction = new CustomzedRecursiveAction(middleNum + 1, endVal);
                invokeAll(leftAction, rightAction);

                Integer leftVal = leftAction.join();
                Integer rightVal = rightAction.join();
                return leftVal + rightVal;
            } else {
                return endVal;
            }
        }
    }


}
