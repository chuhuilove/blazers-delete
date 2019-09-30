package com.chuhui.blazers.algorithm.loadbbalancing.sequence;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.INVOKE_NUM;
import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.IP_LIST;

/**
 * Sequence
 * <p>
 * 顺序策略
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class Sequence {

    private static Integer indexPos = 0;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {


        for (int i = 0; i < INVOKE_NUM; i++) {
            System.err.println(getServer());
        }
    }


    static String getServer() {

        lock.lock();
        try {

            if (indexPos == IP_LIST.size()) {
                indexPos = 0;
            }

            String ip = IP_LIST.get(indexPos++);
            return ip;
        } finally {
            lock.unlock();
        }
    }
}
