package com.chuhui.blazers.algorithm.leetcode.stack;

import static com.chuhui.blazers.algorithm.leetcode.stack.Solution.isValid;
import static com.chuhui.blazers.algorithm.leetcode.stack.Solution.MyStack;

/**
 * SolutionClass
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class BootstrapStack {

    public static void main(String[] args) {

    }
    static void function25(){
        System.err.println(isValid("()"));
    }

    static void function225(){
        MyStack myStack = new MyStack();
        System.err.println(   myStack. empty());
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.err.println(myStack.pop());
        System.err.println(myStack.top());
        System.err.println(myStack.pop());
        System.err.println(myStack.top());
        System.err.println(myStack.pop());

    }


}
