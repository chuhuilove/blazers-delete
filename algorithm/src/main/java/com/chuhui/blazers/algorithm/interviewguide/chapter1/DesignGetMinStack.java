package com.chuhui.blazers.algorithm.interviewguide.chapter1;
/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2018/12/24 20:07
 * Description:设计一个有getMin功能的栈
 */

import java.util.Enumeration;
import java.util.Random;
import java.util.Stack;

/**
 * 题目：实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求：
 * 1.pop、push、getMin操作的时间复杂度都是O(1)
 * 2.设计的栈类型可以使用现成的栈结构
 */


public class DesignGetMinStack {

    /**
     * 每次出栈后，为了保证最小值，时间复杂度到达O(n)
     *
     * @param <E>
     */
    public static class DesignGetMinStack1<E extends Comparable> extends Stack<E> {

        private volatile E minItem = null;

        public DesignGetMinStack1() {
            super();
        }

        @Override
        public E push(E item) {
            synchronized (this) {
                if (minItem == null) {
                    minItem = item;
                } else {
                    minItem = minItem.compareTo(item) < 0 ? minItem : item;
                }
            }
            return super.push(item);
        }

        @Override
        public synchronized E pop() {
            E result = super.pop();
            Enumeration<E> elements = elements();

            minItem = null;

            //如果最后一个元素已经出栈了，当前已经没有元素了，minItem自然也就为null。否则进行迭代，获取最小元素
            if (elements.hasMoreElements()) {
                minItem = elements.nextElement();
                while (elements.hasMoreElements()) {
                    E tempResult = elements.nextElement();
                    minItem = minItem.compareTo(tempResult) < 0 ? minItem : tempResult;
                }
            }

            return result;
        }

        public E getMin() {
            if (elementData == null || elementData.length <= 0 || minItem == null)
                throw new RuntimeException("stack中没有数据");
            return minItem;
        }
    }


    public static class DesignGetMinStack2<E extends Comparable> {

        private Stack<E> stackData;
        private Stack<E> stackMin;

        public DesignGetMinStack2() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(E item) {

            if (stackMin.isEmpty()) {
                stackMin.push(item);
            } else if (item.compareTo(getMin()) < 0) {
                stackMin.push(item);
            }

            stackData.push(item);
        }

        public E pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("current stack is empty");
            }
            E result = stackData.pop();

            if (getMin().compareTo(result) == 0)
                stackMin.pop();
            return result;

        }

        public E getMin() {
            if (stackMin.isEmpty())
                throw new RuntimeException("current stack is empty");
            return stackMin.peek();
        }

    }

    public static class DesignGetMinStack3<E extends Comparable> {

        private Stack<E> stackData;
        private Stack<E> stackMin;

        public DesignGetMinStack3() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(E item) {

            if (stackMin.isEmpty()) {
                stackMin.push(item);
            } else if (item.compareTo(getMin()) < 0) {
                stackMin.push(item);
            } else {
                stackMin.push(stackMin.peek());
            }
            stackData.push(item);
        }

        public E pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("current stack is empty");
            }
            stackMin.pop();
            return stackData.pop();

        }

        public E getMin() {
            if (stackMin.isEmpty())
                throw new RuntimeException("current stack is empty");
            return stackMin.peek();
        }

    }


    public static void main(String[] args) {
        getMinStack1();
        getMinStack2();
        getMinStack3();
    }


    static void getMinStack1() {

        DesignGetMinStack1<Integer> stack1 = new DesignGetMinStack1<>();
        Random random = new Random();
//        IntStream.rangeClosed(1,10).forEach(e->stack1.push(random.nextInt(100)+10));

        stack1.push(10);
        stack1.push(20);
        stack1.push(30);
        stack1.push(15);
        stack1.push(22);
        stack1.push(34);
        stack1.push(11);
        stack1.push(10);
        stack1.push(9);

        System.err.println("未出栈当前最小元素:" + stack1.getMin());
        Integer pop = stack1.pop();
        System.err.println("出栈一个" + pop + "后,当前最小元素" + stack1.getMin());
    }

    static void getMinStack2() {

        DesignGetMinStack2<Integer> stack2 = new DesignGetMinStack2<>();

        stack2.push(10);
        stack2.push(20);
        stack2.push(30);
        stack2.push(15);
        stack2.push(22);
        stack2.push(34);
        stack2.push(11);
        stack2.push(10);
        stack2.push(9);

        System.err.println("未出栈当前最小元素:" + stack2.getMin());
        Integer pop = stack2.pop();
        System.err.println("出栈一个" + pop + "后,当前最小元素" + stack2.getMin());
    }

    static void getMinStack3() {

        DesignGetMinStack3<Integer> stack3 = new DesignGetMinStack3<>();

        stack3.push(10);
        stack3.push(20);
        stack3.push(30);
        stack3.push(15);
        stack3.push(22);
        stack3.push(34);
        stack3.push(11);
        stack3.push(10);
        stack3.push(9);

        System.err.println("未出栈当前最小元素:" + stack3.getMin());
        Integer pop = stack3.pop();
        System.err.println("出栈一个" + pop + "后,当前最小元素" + stack3.getMin());
    }
}



