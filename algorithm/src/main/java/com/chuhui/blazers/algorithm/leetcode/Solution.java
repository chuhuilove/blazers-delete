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
//        ListNode head = linkeNodeGen(new int[]{1, 2, 3, 4, 5});
//        removeNthFromEnd(head, 2);


        System.err.println(rob(new int[]{1, 2, 4, 1, 7, 8, 3}));
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

        if (n == count && count == 1) {
            return null;
        }
        if (n == 1) {
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
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * 注意：
     * <p>
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     * <a href="https://note.youdao.com/ynoteshare1/index.html?id=c7aa27eb65cc3f6fac2b3bec2626a536&type=notebook#/48CE63B3BF3F482BB93B00B796BF6CAC"/>
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {

        int length = num1.length() - num2.length();

        StringBuilder builder = new StringBuilder();

        if (length > 0) {
            //用0 补齐num2
            builder.append(num2).reverse();
            for (int i = 0; i < length; i++) {
                builder.append(0);
            }
            return computer(num1, builder.reverse().toString());
        } else if (length < 0) {
            builder.append(num1).reverse();
            for (int i = 0; i < -length; i++) {
                builder.append(0);
            }
            return computer(num2, builder.reverse().toString());
        }

        return computer(num1, num2);
    }

    private static String computer(String num1Bigger, String num2Smaller) {

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

    /**
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * <p>
     * 返回符合要求的最少分割次数。
     * <p>
     * 示例:
     * <p>
     * 输入: "aab"
     * 输出: 1
     * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     *
     * @param s
     * @return
     */
    public static int minCut(String s) {

        return 0;
    }

    /**
     * 我们有一组包含1和0的网格；其中1表示砖块。 当且仅当一块砖直接连接到网格的顶部，或者它至少有一块相邻（4 个方向之一）砖块不会掉落时，它才不会落下。
     * <p>
     * 我们会依次消除一些砖块。每当我们消除 (i, j) 位置时， 对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这个消除而落下。
     * <p>
     * 返回一个数组表示每次消除操作对应落下的砖块数目。
     * <p>
     * 示例 1：
     * 输入：
     * grid = [[1,0,0,0],[1,1,1,0]]
     * hits = [[1,0]]
     * 输出: [2]
     * 解释:
     * 如果我们消除(1, 0)位置的砖块, 在(1, 1) 和(1, 2) 的砖块会落下。所以我们应该返回2。
     * 示例 2：
     * 输入：
     * grid = [[1,0,0,0],[1,1,0,0]]
     * hits = [[1,1],[1,0]]
     * 输出：[0,0]
     * 解释：
     * 当我们消除(1, 0)的砖块时，(1, 1)的砖块已经由于上一步消除而消失了。所以每次消除操作不会造成砖块落下。注意(1, 0)砖块不会记作落下的砖块。
     * 注意:
     * <p>
     * 网格的行数和列数的范围是[1, 200]。
     * 消除的数字不会超过网格的区域。
     * 可以保证每次的消除都不相同，并且位于网格的内部。
     * 一个消除的位置可能没有砖块，如果这样的话，就不会有砖块落下。
     *
     * @param grid
     * @param hits
     * @return
     */
    public static int[] hitBricks(int[][] grid, int[][] hits) {

        return null;
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * <p>
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * <p>
     * 如果是，则返回最大元素的索引，否则返回-1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     * <p>
     * <p>
     * 提示:
     * <p>
     * nums 的长度范围在[1, 50].
     * 每个 nums[i] 的整数范围在 [0, 99].
     *
     * @param nums
     * @return
     */

    public static int dominantIndex(int[] nums) {

        return -1;

    }



    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     *
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {

        int[] back = new int[nums.length];

        back[0] = nums[0];
        back[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            back[i] = Math.max(back[i - 1],nums[i] + back[i - 2] );
        }
        return back[nums.length - 1];
    }


}
