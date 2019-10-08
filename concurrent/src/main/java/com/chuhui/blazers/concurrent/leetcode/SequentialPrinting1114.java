package com.chuhui.blazers.concurrent.leetcode;

/**
 * SequentialPrinting
 * <p>
 * 力扣多线程-1114 按照顺序打印
 * <p>
 * 执行屏障的问题,三个线程,无论哪个线程先启动,都必须先执行first函数
 * <p>
 * 精选解题:
 * https://leetcode-cn.com/problems/print-in-order/solution/gou-zao-zhi-xing-ping-zhang-shi-xian-by-pulsaryu/
 *
 * 我的解题:
 * https://leetcode-cn.com/problems/print-in-order/solution/zhi-xing-ping-zhang-gou-jian-by-chu-hui-love/
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:
 */
public class SequentialPrinting1114 {


    public static void main(String[] args) {

        final Foo foo = new Foo();
        Thread1 first = new Thread1(foo);
        Thread2 second = new Thread2(foo);
        Thread3 third = new Thread3(foo);

        second.start();
        third.start();
        first.start();
    }

    static class Thread1 extends Thread {
        private Foo foo;

        public Thread1(Foo foo) {
            this.foo = foo;
        }

        @Override
        public void run() {
            try {
                foo.first(() -> System.err.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {
        private Foo foo;

        public Thread2(Foo foo) {
            this.foo = foo;
        }

        @Override
        public void run() {
            try {
                foo.second(() -> System.err.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread3 extends Thread {
        private Foo foo;

        public Thread3(Foo foo) {
            this.foo = foo;
        }

        @Override
        public void run() {
            try {
                foo.third(() -> System.err.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Foo {


        private boolean firstFinished;
        private boolean secondFinished;
        private Object lock = new Object();


        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                printFirst.run();
                firstFinished = true;
                lock.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {

            synchronized (lock) {

                while (!firstFinished) {
                    lock.wait();
                }
                printSecond.run();
                secondFinished = true;
                lock.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {

            synchronized (lock) {
                while (!secondFinished) {
                    lock.wait();
                }
                printThird.run();
            }
        }
    }

}
