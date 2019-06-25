package com.chuhui.blazers.algorithm.tree;

import java.util.Arrays;
import java.util.Map;

/**
 * TreeMapDebug
 *
 * 红黑树的性质:
 * 1. 每个节点或是红色的,或是黑色的.
 * 2. 根节点是黑色的
 * 3. 每个叶子节点(NIL)是黑色的
 * 4. 如果一个节点是红色的,则他的两个叶子节点都是黑色的
 * 5. 对每个节点,从该节点到其所有后代叶节点的简单路径上,均包含相同数目的黑色节点.
 *
 * @author: 纯阳子
 * @Date: 2019/6/25 0025
 * @Description: 从TreeMap中抽出的红黑树
 */
public class TreeMapRedBlackTree<K extends Comparable<? super K>> {
    private Entry<K> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public static void main(String[] args) {

        final TreeMapRedBlackTree<Integer> rbTree = new TreeMapRedBlackTree<>();

        // 45, 12, 38, 49, 10, 37, 9, 19, 22, 83, 67, 70, 11
        // 10,85,15,70,20,60,30,50,65,80,90,40,5,55
        // 不同的插入顺序,对产生的红黑树有影响吗? 有影响,不同的插入顺序,会产生不同的红黑树
        //
        //跟踪代码,查看红黑树的构造
        Arrays.stream(new Integer[]{10,85,15,70,20,60,30,50,65,80,90,40,5,55})
                .forEach(e -> rbTree.insert(e));


        System.err.println(rbTree);
        System.err.println(rbTree);


        /**
         *
         * 验证红黑树不同的插入顺序是否能得到同一个红黑树
         * 1,13,15,17,25,22,21,6,8,11
         *
         * 6,8,11,1,13,15,17,25,22,21.
         *
         * https://blog.csdn.net/q3244676719/article/details/81540830
         *
         * 数据结构与算法 in java
         *
         * 10,85,15,70,20,60,30,50,65,80,90,40,5,55
         *
         * 5,10,85,15,70,90,40,55,20,60,30,50,65,80
         *
         *
         *
         *
         */

    }


    public void insert(K key) {

        if (root == null) {
            root = new Entry<>(key, null);
            return;
        } else {

            Entry<K> parent;
            Entry<K> t = root;
            int cmp;
            do {
                parent = t;
                cmp = key.compareTo(t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;

            } while (t != null);

            Entry e = new Entry<>(key, parent);
            if (cmp < 0)
                parent.left = e;
            else
                parent.right = e;
            fixAfterInsertion(e);
        }

    }

    private void fixAfterInsertion(Entry<K> x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {

            // 获取x的爷爷
            Entry<K> grandfather = parentOf(parentOf(x));

            // 如果x的爹是x的爷爷的左孩子

            if (parentOf(x) == leftOf(grandfather)) {
                // 获取x的爷爷的右孩子
                Entry<K> y = rightOf(grandfather);
                // 如果x的爷爷的右孩子是红色的
                if (colorOf(y) == RED) {
                    // 将x的爹设为黑色的
                    setColor(parentOf(x), BLACK);
                    // 将如果x的爷爷的右孩子设为黑色的
                    setColor(y, BLACK);
                    //将x的爷爷设为红色的
                    setColor(grandfather, RED);

                    x = grandfather;
                } else {

                    // 获取x的爹的右孩子
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * From CLR
     */
    private void rotateLeft(Entry<K> p) {
        if (p != null) {
            Entry<K> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /**
     * From CLR
     */
    private void rotateRight(Entry<K> p) {
        if (p != null) {
            Entry<K> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }


    private static <K> boolean colorOf(Entry<K> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <K> Entry<K> parentOf(Entry<K> p) {
        return (p == null ? null : p.parent);
    }

    private static <K> void setColor(Entry<K> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K> Entry<K> leftOf(Entry<K> p) {
        return (p == null) ? null : p.left;
    }

    private static <K> Entry<K> rightOf(Entry<K> p) {
        return (p == null) ? null : p.right;
    }


    static final class Entry<K > {
        K key;
 
        Entry<K > left;
        Entry<K > right;
        Entry<K > parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        Entry(K key,   Entry<K> parent) {
            this.key = key;
     
            this.parent = parent;
        }




        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;

            return valEquals(key, e.getKey())  ;
        }

        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
          
            return keyHash  ;
        }

       
    }

    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }
}
