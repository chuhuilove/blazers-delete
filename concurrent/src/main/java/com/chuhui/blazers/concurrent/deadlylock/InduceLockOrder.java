package com.chuhui.blazers.concurrent.deadlylock;

import com.chuhui.blazers.concurrent.deadlylock.DynamicOrderDeadlock.Account;
import com.chuhui.blazers.concurrent.deadlylock.DynamicOrderDeadlock.DollarAmount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/3/1 19:34
 * Description: 通过锁顺序来避免动态顺序死锁
 */

/**
 * 解决的问题
 *
 * @see DynamicOrderDeadlock#noThreadSafety
 * <p>
 * 在 {@link DynamicOrderDeadlock#noThreadSafety }的问题中,
 * 其本质问题是由于传递的参数顺序不一致,在cpu的调度中,出现了锁的顺序不一致嵌套,如{@link LeftRightDeadlock}中的问题,只不过是动态出现的.
 * 若解决该问题,必须控制锁的顺序,并在整个应用程序中都按照这个顺序来获取锁.
 * <p>
 * 在指定锁的顺序时,可以使用   System.identityHashCode() 函数,该函数返回由 Object.hashCode 返回的值.
 */
public class InduceLockOrder {

    private static final Object tieLock = new Object();

    public void transferMoney(final DynamicOrderDeadlock.Account fromAcct,
                              final Account toAcct,
                              final DollarAmount amount) {
        class Helper {
            public void transfer() {
                if (fromAcct.getBalance().compareTo(amount) < 0) {

                    throw new RuntimeException("账户余额不足,无法转账");
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        /**
         * 通过获取两个对象的hash值,来确保锁的顺序一致
         */
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            /**
             * 如果两个对象的hash相同(极少数情况),此时必须通过某种任意的方法来决定锁的顺序,
             * 而可能又会重新引入死锁.为了避免这种情况,可以使用"加时赛"(Tie-Breaking)锁.在获得两个Account锁之前,首先获得"加时赛"锁,
             * 从而保证了每次只有一个线程以未知的顺序获得这两个锁,从而消除了死锁发生的可能性.
             * 如果经常会出现散列冲突的情况,从而那么这种技术将会成为并发性的瓶颈,但由于System.identityHashCode出现散列冲突的频率非常低,
             * 因此这项技术以最小的代价,换来最大的安全性.
             *
             * 因为在Account中包含一个唯一的,不可变的且具备可比性的账户值,那么制定锁的顺序就更容易了,通过对比账户值,
             * 进行排序,因而不需要使用"加时赛"锁.
             *
             */
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Account fromAccount = new Account();
        fromAccount.setBalance(new DollarAmount(10000));

        Account toAccount = new Account();
        toAccount.setBalance(new DollarAmount(20000));

        InduceLockOrder induceLockOrder = new InduceLockOrder();

        int i = 0;

        while (i < Integer.MAX_VALUE) {
            threadSafety(induceLockOrder, fromAccount, toAccount);
        }

    }

    /**
     * 线程安全 一些线程执行从fromAccount转到toAccount 1元,一些线程执行从toAccount转到fromAccount 1元.
     *
     * @param induceLockOrder
     * @param fromAccount
     * @param toAccount
     */
    static void threadSafety(InduceLockOrder induceLockOrder, Account fromAccount, Account toAccount) {
        final DollarAmount fromTo = new DollarAmount(1);

        List<Thread> threads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors() << 2)
                .mapToObj(e -> new Thread(() -> induceLockOrder.transferMoney(fromAccount, toAccount, fromTo)))
                .collect(Collectors.toList());

        final DollarAmount toFrom = new DollarAmount(1);

        List<Thread> toThreads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors() << 2)
                .mapToObj(e -> new Thread(() -> induceLockOrder.transferMoney(toAccount, fromAccount, toFrom)))
                .collect(Collectors.toList());

        threads.addAll(toThreads);

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.err.println("线程安全已经完成,当前fromCount余额:" + fromAccount.getBalance().getAmount() + " 当前toAccount余额:" + toAccount.getBalance().getAmount());

    }


}
