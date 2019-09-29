package com.chuhui.blazers.algorithm.loadbbalancing;

import java.util.ArrayList;
import java.util.List;

/**
 * DataUtils
 * <p>
 * 产生数据的工具
 *
 * @author: cyzi
 * @Date: 2019/9/29 0029
 * @Description:TODO
 */
public class DataUtils {

    final public static List<String> IP_LIST = new ArrayList<>();
    final  public static List<WeightIp> WEIGHT_IPS=new ArrayList<>();

    final  public static List<WeightIp> SAME_WEIGHT_IPS=new ArrayList<>();

    final public static int INVOKE_NUM = 10;

    static {
        {
            /**
             * 初始化IP_LIST
             */
            for (int i = 0; i < 10; i++) {
                IP_LIST.add("192.168.0." + i);
            }
        }

        {
            /**
             * 初始化WEIGHT_IPS
             */
            WEIGHT_IPS.add(new WeightIp("A",12));
            WEIGHT_IPS.add(new WeightIp("B",3));
            WEIGHT_IPS.add(new WeightIp("C",5));
        }
        {
            /**
             *初始化SAME_WEIGHT_IPS
             */
            SAME_WEIGHT_IPS.add(new WeightIp("A",5));
            SAME_WEIGHT_IPS.add(new WeightIp("B",5));
            SAME_WEIGHT_IPS.add(new WeightIp("C",5));
            SAME_WEIGHT_IPS.add(new WeightIp("D",5));
            SAME_WEIGHT_IPS.add(new WeightIp("E",5));

        }
    }

    public static class WeightIp {
        /**
         * 服务器ip
         */
        private String ip;
        /**
         * 权重
         */
        private int weight;

        public WeightIp(String ip, int weight) {
            this.ip = ip;
            this.weight = weight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        int i=10;
        int b=10;

        System.err.println(i^b);

    }


}
