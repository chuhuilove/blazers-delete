package com.chuhui.blazers.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

        int[] arrays = new int[5];
        Arrays.fill(arrays, 10);

        Arrays.stream(arrays).forEach(System.err::println);

        arrayMove(arrays);
        System.err.println("改动后======");

        Arrays.stream(arrays).forEach(System.err::println);

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
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * 注意：
     * <p>
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {

        return null;
    }

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * 示例 1:
     *
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     *
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     *
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {

        return null;
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
