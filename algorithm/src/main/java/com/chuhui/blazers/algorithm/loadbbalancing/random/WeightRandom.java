package com.chuhui.blazers.algorithm.loadbbalancing.random;


import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.WeightIp;

import java.text.MessageFormat;
import java.util.Random;

import static com.chuhui.blazers.algorithm.loadbbalancing.DataUtils.*;

/**
 * WeightRandom
 * 带有权重的随机策略
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:
 */
public class WeightRandom {


    public static void main(String[] args) {


        int aTotal = 0;
        int bTotal = 0;
        int cTotal = 0;


        int executeNum = INVOKE_NUM * 2000;

        for (int i = 0; i < executeNum; i++) {
            String ip = getIp();

            if ("A".equals(ip)) {
                ++aTotal;
                continue;
            }

            if ("B".equals(ip)) {
                ++bTotal;
                continue;
            }

            if ("C".equals(ip)) {
                ++cTotal;
            }

        }

        String message = MessageFormat.format("执行{0}次,a获取:{1}次,b获取{2}次,c获取{3}次 ", executeNum, aTotal, bTotal, cTotal);

        System.err.println(message);
    }


   public static int getWeightSum() {
        return WEIGHT_IPS.stream().mapToInt(e -> e.getWeight()).reduce(0, (a, b) -> a + b);
    }

    /**
     * 检查权重值是否都一致
     *
     * @return
     */
   public static boolean checkSame() {
        return WEIGHT_IPS.stream().mapToInt(e -> e.getWeight()).reduce(WEIGHT_IPS.get(0).getWeight(), (a, b) -> a ^ b) == 0;
    }


    static String getIp() {
        int weightSum = getWeightSum();
        int randomPos = new Random().nextInt(weightSum);


        // 判断list中的权重值是否都一致
        if (!checkSame()) {

            for (WeightIp ip : WEIGHT_IPS) {
                int value = ip.getWeight();
                if (randomPos < value) {
                    return ip.getIp();
                }
                randomPos = randomPos - value;
            }
        }

        return WEIGHT_IPS.get(new Random().nextInt(WEIGHT_IPS.size())).getIp();
    }


}
