package com.chuhui.blazers.algorithm.loadbbalancing.sequence;

import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.WeightIp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.INVOKE_NUM;
import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.WEIGHT_IPS;
import static com.chuhui.blazers.algorithm.loadbbalancing.random.WeightRandom.checkSame;
import static com.chuhui.blazers.algorithm.loadbbalancing.random.WeightRandom.getWeightSum;

/**
 * SmoothWeightSequence
 * 平滑加权轮询
 *
 * @author: cyzi
 * @Date: 2019/9/30 0030
 * @Description:TODO
 */
public class SmoothWeightSequence {

    private static Lock lock = new ReentrantLock();


    public final static Map<String, WeightIp> WEIGHT_IP_MAP= new HashMap<>();

    static {
        // 为了不影响原始WEIGHT_IPS中的值,在这里新创建一个map
        WEIGHT_IPS.forEach(e -> WEIGHT_IP_MAP.put(e.getIp(), new WeightIp(e.getIp(), e.getWeight(), e.getWeight())));
    }


    public static void main(String[] args) {

        for (int i = 1; i <= INVOKE_NUM*2 ; i++) {
            System.err.printf(getIp());
        }
    }


    static String getIp() {

        lock.lock();
        try {

            // 获取权重之和,因为并没有修改实际的权重,所以这个函数还能继续使用
            int weightSum = getWeightSum();

            // 检查是否所有的权重都一致,使用原因,同上
            boolean same = checkSame();

            if (!same) {

                // 先找到虚拟权重最大的那ip
                WeightIp maxWeightIp  = WEIGHT_IP_MAP.values().stream().max(Comparator.comparing(WeightIp::getVirtualWeight)).get();

                // 最大的那个ip的虚拟权重,减去总的实际权重
                maxWeightIp.setVirtualWeight(maxWeightIp.getVirtualWeight() - weightSum);

                // 将所有ip的虚拟权重统一设置为虚拟权重加上实际权重
                WEIGHT_IP_MAP.forEach((k, v) -> v.setVirtualWeight(v.getVirtualWeight() + v.getWeight()));

                return maxWeightIp.getIp();

            }
            //如果所有权重都一致,则随机返回一个ip
            return WEIGHT_IPS.get(new Random().nextInt(WEIGHT_IPS.size())).getIp();
        } finally {
            lock.unlock();
        }
    }


}
