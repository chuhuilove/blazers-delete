package com.chuhui.blazers.algorithm.leetcode;

import java.util.*;

/**
 * Created by Administrator on 2019/5/10 0010.
 */
public class Solution {

    /**
     * 1.两数之和
     * <a href="https://note.youdao.com/ynoteshare1/index.html?id=c7aa27eb65cc3f6fac2b3bec2626a536&type=notebook#/C5ACB011A0454822B8E94F4502696367"/>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        //存储数组的值和下标
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {

            int other = target - nums[i];

            if (map.containsKey(other)) {
                return new int[]{map.get(other), i};
            }
            map.put(nums[i], i);
        }

        return null;
    }

    /**
     * 2.两数之和
     * <a href="https://note.youdao.com/ynoteshare1/index.html?id=c7aa27eb65cc3f6fac2b3bec2626a536&type=notebook#/F89217057BDA4681811110B5C02D2FDE"/>
     *
     * @param l1
     * @param l2
     * @return
     */
    public static SolutionClass.ListNode addTwoNumbers(SolutionClass.ListNode l1, SolutionClass.ListNode l2) {


        StringBuilder builder1 = new StringBuilder();

        while (l1.getNext() != null) {
            builder1.append(l1.getVal());
            l1 = l1.getNext();
        }
        builder1.append(l1.getVal());

        StringBuilder builder2 = new StringBuilder();

        while (l2.getNext() != null) {
            builder2.append(l2.getVal());
            l2 = l2.getNext();
        }
        builder2.append(l2.getVal());

        //将所有数字全部提取出来
        //短一些的,直接补0

        int diffCount = builder1.length() - builder2.length();

        if (diffCount > 0) {
            //builder2 补0
            for (int i = 0; i < diffCount; i++) {
                builder2.append(0);
            }
        } else if (diffCount < 0) {
            //builder1 补0
            for (int i = 0; i < -diffCount; i++) {
                builder1.append(0);
            }
        }

        //目前为止,字符串都是反序的
        //将字符串正序过来,准备进行相加
        final String lastr1 = builder1.reverse().toString();
        final String lastr2 = builder2.reverse().toString();


        StringBuilder builder = new StringBuilder();

        int tempRes = 0;
        int carry = 0;
        for (int i = lastr1.length() - 1; i >= 0; i--) {

            tempRes = Integer.valueOf(String.valueOf(lastr1.charAt(i))) + Integer.valueOf(String.valueOf(lastr2.charAt(i))) + carry;
            if (tempRes >= 10) {
                builder.append(tempRes % 10);
                carry = 1;
            } else {
                builder.append(tempRes);
                carry = 0;
            }
        }
        if (carry == 1) {
            builder.append(carry);
        }

        SolutionClass.ListNode rootNode = new SolutionClass.ListNode(Integer.valueOf(String.valueOf(builder.charAt(0))));

        SolutionClass.ListNode preNode = rootNode;

        for (int i = 1; i < builder.length(); i++) {
            preNode.setNext(new SolutionClass.ListNode(Integer.valueOf(String.valueOf(builder.charAt(i)))));
            preNode = preNode.getNext();
        }
        return rootNode;

    }


    /**
     * 3. 无重复字符的最长子串
     * <a href="https://note.youdao.com/ynoteshare1/index.html?id=c7aa27eb65cc3f6fac2b3bec2626a536&type=notebook#/9E62A9E284E94C279B6C5D73745A120E"/>
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = linkeNodeGen(new int[]{1, 2, 3, 4, 5});
        removeNthFromEnd(head, 2);
    }

    private static ListNode linkeNodeGen(int[] nums) {
        ListNode rootNode = new ListNode(nums[0]);

        ListNode parentNode = rootNode;

        for (int i = 1; i < nums.length; i++) {
            parentNode.next = new ListNode(nums[i]);
            parentNode = parentNode.next;
        }

        return rootNode;
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode rootNode = head;
        Map<Integer, ListNode> map = new HashMap();
        int count = 1;
        map.put(count, rootNode);
        while (rootNode.next != null) {
            rootNode = rootNode.next;
            map.put(++count, rootNode);
        }

        if(n==count && count==1){
            return null;
        }
        if (n == 1 ) {
            map.get(count - 1).next = null;
            return head;
        }
        if (n == count) {
            map.get(1).next = null;
            return map.get(2);
        }

        ListNode preNode = map.get(count - n);
        ListNode nextNode = map.get(count - n + 2);
        preNode.next = nextNode;
        return head;

    }
    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     * <p>
     * 输入: coins = [2], amount = 3
     * 输出: -1
     */


    public int coinChange(int[] coins, int amount) {

        return 0;
    }

}
