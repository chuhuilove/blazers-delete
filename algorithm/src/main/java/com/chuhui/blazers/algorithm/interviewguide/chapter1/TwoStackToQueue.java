package com.chuhui.blazers.algorithm.interviewguide.chapter1;/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2018/12/24 20:27
 * Description:由两个栈组成的队列
 */

import java.util.Enumeration;
import java.util.Stack;

public class TwoStackToQueue<E> {

    //栈是先进后出
    //队列是现今先出

    private Stack<E> pushStack = new Stack<>();
    private Stack<E> pollStack = new Stack<>();

    public TwoStackToQueue() {

    }

    public TwoStackToQueue(Stack<E> pushStack, Stack<E> pollStack) {
        this.pollStack = pollStack;
        this.pushStack = pushStack;
    }


    public E add(E item) {

        Enumeration<E> elements = pushStack.elements();
        pollStack.clear();
        while (elements.hasMoreElements())
            pollStack.push(elements.nextElement());

        return pushStack.push(item);

    }


}
