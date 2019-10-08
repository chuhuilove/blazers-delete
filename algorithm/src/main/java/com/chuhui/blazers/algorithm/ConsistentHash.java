package com.chuhui.blazers.algorithm;

import com.chuhui.blazers.algorithm.loadbbalancing.DataUtils;
import sun.font.FontRunIterator;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ConsistentHash
 * <p>
 * 一致性hash算法
 *
 * @author: 纯阳子
 * @date: 2019/9/30
 */
public class ConsistentHash {


    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static int VIRTUAL_NODE_COUNT = 200;

    static {
        List<String> ipList = DataUtils.IP_LIST;

        for (String ip : ipList) {

            // 为每个真实节点添加200个虚拟节点
            for (int i = 0; i < VIRTUAL_NODE_COUNT; i++) {

                int hash = getHash(ip + "VIRTUAL_NODE" + i);
                virtualNodes.put(hash, ip);
            }
        }

    }


    /**
     * 获取字符串的hash值,从网上抄来的
     * @param str
     * @return
     */
    static int getHash(String str) {


        final int p = 16777619;
        int hash = (int) 2166136261L;

        for (int i = 0; i < str.length(); i++){
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


    static String getServer(String client) {

        int hash = getHash(client);

        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);

        Integer nodeIndex = subMap.firstKey();

        if (nodeIndex == null) {
            nodeIndex = virtualNodes.firstKey();
        }

        return subMap.get(nodeIndex);
    }


    public static void main(String[] args) {

        for (int i = 0; i < VIRTUAL_NODE_COUNT; i++) {

            System.err.println(getServer("client" + i));

        }


    }


}
