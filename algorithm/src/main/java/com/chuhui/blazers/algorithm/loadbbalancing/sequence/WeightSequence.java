package com.chuhui.blazers.algorithm.loadbbalancing.sequence;

import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.WeightIp;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.INVOKE_NUM;
import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.WEIGHT_IPS;
import static com.chuhui.blazers.algorithm.loadbbalancing.random.WeightRandom.checkSame;
import static com.chuhui.blazers.algorithm.loadbbalancing.random.WeightRandom.getWeightSum;

/**
 * WeightSequence
 * 加权轮询
 *
 * @author: cyzi
 * @Date: 2019/9/30 0030
 * @Description:TODO
 */
public class WeightSequence {

    /**
     * 调用序号
     */
    private static AtomicLong invokeNum = new AtomicLong(1);

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 1; i <= INVOKE_NUM * 3 + 6; i++) {
            System.err.printf(getIp());
        }
    }


    static String getIp() {

        lock.lock();

        try {
            // 调用序号递增
            long currentNum = invokeNum.getAndIncrement();

            // 获取权重之和
            int weightSum = getWeightSum();

            // 检查是否所有的权重都一致
            boolean same = checkSame();

            if (!same) {
                long modeRes = currentNum % weightSum;
                // 如果到了一个周期,需要特殊值处理
                if (modeRes == 0) {
                    modeRes = weightSum;
                }

                for (WeightIp weightIp : WEIGHT_IPS) {

                    if (modeRes <= weightIp.getWeight()) {
                        return weightIp.getIp();
                    }

                    modeRes = modeRes - weightIp.getWeight();
                }
            }

            // 如果权重都一致,则随机返回一个
            return WEIGHT_IPS.get(new Random().nextInt(WEIGHT_IPS.size())).getIp();
        } finally {
            lock.unlock();
        }
    }


}
