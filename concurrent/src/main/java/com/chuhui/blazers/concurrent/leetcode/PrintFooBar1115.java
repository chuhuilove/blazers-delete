package com.chuhui.blazers.concurrent.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * PrintFooBar1115
 * 力扣多线程-1115 交替打印Foobar
 * 水平还是欠缺啊...
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class PrintFooBar1115 {


    public static void main(String[] args) {

        final FooBar fooBar = new FooBar(2);


        Thread1 first = new Thread1(fooBar);
        Thread2 second = new Thread2(fooBar);

        first.start();
        second.start();

    }


    static class Thread1 extends Thread {
        private FooBar fooBar;

        public Thread1(FooBar fooBar) {
            this.fooBar = fooBar;
        }

        @Override
        public void run() {
            try {
                fooBar.foo(() -> System.err.printf("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {
        private FooBar fooBar;

        public Thread2(FooBar fooBar) {
            this.fooBar = fooBar;
        }


        @Override
        public void run() {
            try {
                fooBar.bar(() -> System.err.printf("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    static class FooBar {
        private int n;

        private CountDownLatch latch=new CountDownLatch(1);

        CyclicBarrier barrier=new CyclicBarrier(2);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

//                if(1==barrier.getNumberWaiting()){
                    printFoo.run();
//                }

//                latch.countDown();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
//                latch.await();

                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                printBar.run();
//                latch=new CountDownLatch(1);
            }
        }
    }


}
