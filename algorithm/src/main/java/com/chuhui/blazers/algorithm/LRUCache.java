package com.chuhui.blazers.algorithm;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * LRUAlgorithm Lru算法
 * 力扣
 *
 * @author: 纯阳子
 * @Date: 2019/4/28
 * @Description:TODO
 */
public class LRUCache {


    private List<Entry> list;

    private static int MAX_CAPACITY;

    public LRUCache(int capacity) {
        list = new LinkedList<>();

        MAX_CAPACITY = capacity;
    }

    public int get(int key) {

        Entry entry = new Entry(key, null);

        int index = list.indexOf(entry);
        if (index < 0) {
            return index;
        }
        return (int) list.get(index).value;
    }

    public void put(int key, int value) {

        int index = get(key);

        if (index >= 0) {
            list.get(index - 1).value = value;
            return;
        }


        if (list.size() == MAX_CAPACITY) {
            list.remove(list.size() - 1);
            list.add(0, new Entry<>(key, value));
        } else {
            list.add(new Entry<>(key, value));
        }


    }

    static class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }


    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(2);
//
//        cache.put(2, 1);
//        cache.put(2, 2);
//        // 返回  1
//        System.err.println("2 找到:"  + cache.get(2));
//
//        cache.put(1, 1);
//        cache.put(4, 1);
//        System.err.println("-1 找到:"+cache.get(2));

//        int[] arrays = new int[5];
//        Arrays.fill(arrays, 10);
//
//        Arrays.stream(arrays).forEach(System.err::println);
//
//        arrayMove(arrays);
//        System.err.println("改动后======");
//
//        Arrays.stream(arrays).forEach(System.err::println);



        final StringBuilder builder=new StringBuilder(9);

        LongStream.rangeClosed(1,20000L).forEach(e->builder.append(9));


        String str=builder.toString();

        BigInteger a=new BigInteger(str);

        System.err.println("BigInteger开始:"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));

        BigInteger multiply1 = a.multiply(new BigInteger(str));

        System.err.println(multiply1.toString());
        System.err.println("BigInteger结束:"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));

        System.err.println("==========================================================================");

        System.err.println("自定义开始:"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));

        System.err.println(multiply(str, str));
        System.err.println("自定义结束:"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));


    }


    public static void arrayMove(int[] arrays) {


        for (int i = arrays.length - 2; i > 0; i--) {
            arrays[i - 1] = arrays[i];
        }

        arrays[0] = Integer.MAX_VALUE;

    }


    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * <p>
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

    }


    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * <p>
     * 示例 1:
     * <p>
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     * <p>
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     * <p>
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {


        //一定要区分出谁长,谁短,短的一定要在后面

        int length = num1.length() - num2.length();
        if (length >= 0) {
            return computerMultiply(num1, num2);
        } else {
            return computerMultiply(num2, num1);
        }


    }

    public static String computerMultiply(String num1, String num2) {

        List<StringBuilder> builders = new ArrayList<>();

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        for (int i = num2.length() - 1, k = 0; i >= 0; k++, i--) {

            StringBuilder builder = new StringBuilder();
            int differ = 0;

            for (int j = num1.length() - 1; j >= 0; j--) {
                int i1 = Integer.valueOf(String.valueOf(num1.charAt(j))) * Integer.valueOf(String.valueOf(num2.charAt(i))) + differ;
                if (i1 >= 10) {
                    builder.append(i1 % 10);
                    differ = i1 / 10;
                } else {
                    builder.append(i1);
                    differ = 0;
                }
            }
            if (differ != 0) {
                builder.append(differ);
            }
            builder.reverse();
            if (k != 0) {
                for (int j = 0; j < k; j++) {
                    builder.append(0);
                }
            }
            builders.add(builder);
        }

        //将builders里面的数据相加
        if (builders.size() == 1){
            return builders.get(0).toString();
        }

        return  builders.stream().reduce((a,b)->new StringBuilder(addStrings(a.toString(),b.toString()))).get().toString();
    }

    public static String addStrings(String num1, String num2) {
        int length = num1.length() - num2.length();

        StringBuilder builder = new StringBuilder();

        if (length > 0) {
            //用0 补齐num2
            builder.append(num2).reverse();
            for (int i = 0; i < length; i++) {
                builder.append(0);
            }
            return computer2(num1, builder.reverse().toString());
        } else if (length < 0) {
            builder.append(num1).reverse();
            for (int i = 0; i < -length; i++) {
                builder.append(0);
            }
            return computer2(num2, builder.reverse().toString());
        }

        return computer2(num1, num2);
    }

    private static String computer2(String num1Bigger, String num2Smaller) {

        StringBuilder builder = new StringBuilder();

        int differ = 0;
        for (int i = num1Bigger.length() - 1; i >= 0; i--) {

            int i1 = Integer.valueOf(String.valueOf(num1Bigger.charAt(i))) + Integer.valueOf(String.valueOf(num2Smaller.charAt(i))) + differ;

            if (i1 >= 10) {
                builder.append(i1 % 10);
                differ = 1;
            } else {
                builder.append(i1);
                differ = 0;
            }
        }
        if (differ == 1) {
            builder.append(differ);
        }
        return builder.reverse().toString();
    }


/**
 *运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2   缓存容量   );
 *
 *cache.put(1,1);
 *cache.put(2,2);
 *cache.get(1);       // 返回  1
 *cache.put(3,3);    // 该操作会使得密钥 2 作废
 *cache.get(2);       // 返回 -1 (未找到)
 *cache.put(4,4);    // 该操作会使得密钥 1 作废
 *cache.get(1);       // 返回 -1 (未找到)
 *cache.get(3);       // 返回  3
 *cache.get(4);       // 返回  4
 */


/**
 * ["LRUCache","put","put","get","put","put","get"]
 * [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
 */

}
