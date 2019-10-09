package com.chuhui.blazers.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Administrator on 2019/5/10 0010.
 */
public class SolutionStack {


    /**
     * 20 判断有效括号
     *
     * 我的解题:
     * https://leetcode-cn.com/problems/valid-parentheses/solution/yong-zhan-lai-shi-xian-by-chuhuilove/
     *
     * @param s
     * @return
     */
    static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (Character chara : s.toCharArray()) {

            if (stack.size() == 0) {
                stack.push(chara);
                continue;
            }

            Character peek = stack.peek();

            boolean isMatch = false;

            switch (peek) {
                case '(':
                    if (chara == ')') {
                        isMatch = true;
                    }
                    break;
                case '[':
                    if (chara == ']') {
                        isMatch = true;
                    }
                    break;
                case '{':
                    if (chara == '}') {
                        isMatch = true;
                    }
                    break;
                default:
                    isMatch = false;
                    break;
            }

            if (isMatch) {
                stack.pop();
            } else {
                stack.push(chara);
            }
        }

        return stack.size() == 0;
    }

    /**
     * 225 用队列实现栈
     *
     * 我的解题:
     * https://leetcode-cn.com/problems/implement-stack-using-queues/solution/zuo-bi-he-bu-zuo-bi-shi-xian-by-chuhuilove/
     */
    public static class MyStack {

        private Queue<Integer> queue;
        private Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue = new ArrayDeque<>();
            queue2=new ArrayDeque<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {

            queue2.clear();
            queue2.addAll(queue);

            queue.clear();
            // 保持当前添加的元素在队头
            queue.add(x);
            while(!queue2.isEmpty()) {
                queue.add(queue2.poll());
            }

        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {

            if (!queue.isEmpty()) {
                return queue.poll();
            }
            throw new NullPointerException("MyStack is null");
        }

        /**
         * Get the top element.
         */
        public int top() {

            if (!queue.isEmpty()) {
                return queue.peek();
            }

            throw new NullPointerException("MyStack nothing");
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();
        }
    }


}
