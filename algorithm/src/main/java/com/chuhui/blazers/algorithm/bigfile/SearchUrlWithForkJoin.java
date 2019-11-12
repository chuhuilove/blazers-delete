package com.chuhui.blazers.algorithm.bigfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * SearchUrlWithForkJoin
 *
 * @author: cyzi
 * @Date: 2019/11/12 0012
 * @Description:TODO
 */
public class SearchUrlWithForkJoin {


    public static void main(String[] args) {

        CustomzedRecursiveAction action = new CustomzedRecursiveAction(1, 100);
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

            System.err.println(Thread.currentThread().getName());

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
