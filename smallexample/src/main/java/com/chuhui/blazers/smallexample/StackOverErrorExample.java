package com.chuhui.blazers.smallexample;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * StackOverErrorExample
 *
 * @author: cyzi
 * @Date: 2019/8/27 0027
 * @Description:TODO
 */
public class StackOverErrorExample {

    static int count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    createObject();
                }
            }).start();
        }
    }


    static class User {
        private Integer id;
        private String name;
    }


    /**
     * OutOfMemoryError
     */
    static void createObject() {
        User user = new User();
        user.id = ThreadLocalRandom.current().nextInt();
        user.name = UUID.randomUUID().toString();
    }


    /**
     * 死递归
     * StackOverflowError
     */
    static void loop() {

        System.err.println("current count:" + count++);
        loop();

    }


}
