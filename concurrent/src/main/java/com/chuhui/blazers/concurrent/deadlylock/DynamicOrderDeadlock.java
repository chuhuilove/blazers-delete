package com.chuhui.blazers.concurrent.deadlylock;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/3/1 17:21
 * Description:动态的锁顺序死锁
 */

/**
 * 解决方案
 *
 * @see InduceLockOrder
 */
public class DynamicOrderDeadlock {


    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     DollarAmount amount) {
        synchronized (fromAccount) {
            synchronized (toAccount) {

                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new RuntimeException("无法完成转账:fromAccount:" + fromAccount.balance.amount + " toAccount:" + toAccount.balance.amount);
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    public static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation

        private int amount;

        public DollarAmount(int amount) {

            this.amount = amount;
        }

        //添加
        public DollarAmount add(DollarAmount d) {
            amount += d.amount;
            return this;
        }

        //减少
        public DollarAmount subtract(DollarAmount d) {
            amount -= d.amount;
            return this;
        }

        public int compareTo(DollarAmount dollarAmount) {
            return Integer.valueOf(amount).compareTo(dollarAmount.amount);
        }

        int getAmount() {
            return amount;
        }
    }

    public static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        //借入
        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        //        借出
        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }

        void setBalance(DollarAmount balance) {
            this.balance = balance;
        }


    }


    public static void main(String[] args) {
        Account fromAccount = new Account();
        fromAccount.balance = new DollarAmount(20000);

        Account toAccount = new Account();
        toAccount.balance = new DollarAmount(30000);
        DollarAmount amount = new DollarAmount(500);

        threadSafety(fromAccount, toAccount, amount);

        int i = 0;
        while (i < Integer.MAX_VALUE) {
            noThreadSafety(fromAccount, toAccount);
            i++;
        }

    }

    /**
     * 线程不安全,从fromAccount账户转到toAccount 1元,然后再从toAccount中转到fromAccount 2元
     * 并不是每一次都会发生死锁....所以在调用时循环调用
     *
     * @param fromAccount
     * @param toAccount
     */
    static void noThreadSafety(Account fromAccount, Account toAccount) {


        /**
         * 当两个线程同时调用transferMoney,线程X由fromAccount向toAccount转账.线程Y由toAccount向fromAccount转账时,就会发生死锁
         * 如果执行时序不当(cpu调度的问题),线程X持有fromAccount的锁而等待toAccount的锁,而此时线程Y持有toAccount的锁而等待fromAccount的锁.
         */

        final DollarAmount fromTo = new DollarAmount(1);

        List<Thread> threads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors())
                .mapToObj(e -> new Thread(() -> transferMoney(fromAccount, toAccount, fromTo)))
                .collect(Collectors.toList());

        final DollarAmount toFrom = new DollarAmount(2);

        List<Thread> toThreads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors())
                .mapToObj(e -> new Thread(() -> transferMoney(toAccount, fromAccount, toFrom)))
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

        System.err.println("线程不安全已经完成,当前fromCount余额:" + fromAccount.balance.amount + " 当前toAccount余额:" + toAccount.balance.amount);
    }

    /**
     * 线线程安全  从 fronAccount中减少500元,转账进toAccount中
     * 每个线程都遵循 先锁fromAccount再锁toAccount,最后释放fromAccount和toAccount...
     * 遵循 {@link LeftRightDeadlock#rightLeftFigure} 的思想,不会产生死锁
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     */
    static void threadSafety(Account fromAccount, Account toAccount, DollarAmount amount) {


        List<Thread> threads = IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors())
                .mapToObj(e -> new Thread(() -> transferMoney(fromAccount, toAccount, amount)))
                .collect(Collectors.toList());

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.err.println("线程安全已经完成,当前fromCount余额:" + fromAccount.balance.amount + " 当前toAccount余额:" + toAccount.balance.amount);
    }

}
