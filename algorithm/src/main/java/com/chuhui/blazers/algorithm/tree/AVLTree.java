package com.chuhui.blazers.algorithm.tree;

import java.util.*;

/**
 * 二叉平衡树
 *
 * @author: 纯阳子
 * @Date: 2019/5/6
 * @Description:TODO
 */

public class AVLTree<E extends Comparable<? super E>> {
    /**
     * 二叉平衡树本质上是一个二叉搜索树
     * 最大的特点是每个节点的左子树和右子树的高度差至多等于1
     * <p>
     * 二叉树上节点的左子树深度减去右子树深度的值称为平衡因子(BF)
     * <p>
     * 二叉平衡树的最少节点数S(h)和其高度h拥有斐波那契数列密切相关
     * S(h)=S(h-1)+S(h-2)+1
     * S(1)=1 S(2)=2
     * <p>
     * 不平衡出现的情况:
     * 1. 节点node的左儿子的左子树进行一次插入
     * 2. 节点node的左儿子的右子树进行一次插入
     * 3. 节点node的右儿子的左子树进行一次插入
     * 4. 节点node的右儿子的右子树进行一次插入
     */

    private AVLNode<E> root;

    private static final int ALLOWED_IMBALANCE = 1;

    /**
     * 插入节点
     *
     * @param key
     */
    public void insert(E key) {
        root = insert(key, root);
    }

    /**
     * 搜索key是否存在AVL中
     *
     * @param key
     * @return
     */
    public boolean search(E key) {
        return search(key, root);
    }

    /**
     * 删除某个节点
     * @param key
     * @return
     */
    public AVLNode remove(E key) {
        return remove(key, root);
    }

    private AVLNode remove(E key, AVLNode<E> node) {

        if (node == null) {
            return node;
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = remove(key, node.left);
        } else if (compareResult > 0) {
            node.right = remove(key, node.right);
        } else if (node.right != null && node.left != null) {

            AVLNode<E> min = findMin(node.right);
            node.key =min.key;
            node.right = remove(key, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }

        return balance(node);
    }


    private boolean search(E key, AVLNode<E> node) {

        if (node == null)
            return false;


        int compareResult = node.key.compareTo(key);

        if (compareResult == 0) {
            return true;
        }

        if (compareResult > 0) {
            return search(key, node.getLeft());
        }
        return search(key, node.getRight());
    }


    private AVLNode<E> insert(E key, AVLNode<E> node) {
        if (node == null) {
            return new AVLNode<>(key);
        }

        int compareResult = key.compareTo(node.getKey());

        if (compareResult < 0) {
            node.setLeft(insert(key, node.getLeft()));
        } else if (compareResult > 0) {
            node.setRight(insert(key, node.getRight()));
        } else {

        }

        return balance(node);
    }

    /**
     * 自平衡机制
     * 怎么就理解不了呢??
     *
     * @param t
     * @return
     */
    private AVLNode<E> balance(AVLNode<E> t) {

        //FIXME 2019年6月24日15:52:55
        // 待定..清清脑子


        // todo 2019年6月25日13:38:26
        // 已经能手绘平衡二叉树了
        // 再仔细思考一下代码

        if (t == null) {
            return t;
        }

        // 左儿子比右儿子高2
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        }
        //右儿子比左儿子高2
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            //右儿子的右子树的高度 大于等于右儿子的左子树的高度
            if (height(t.right.right) >= height(t.right.left)) {

                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     * 左单旋
     */
    private AVLNode<E> rotateWithLeftChild(AVLNode<E> k2) {
        AVLNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     * 右单旋
     */
    private AVLNode<E> rotateWithRightChild(AVLNode<E> k1) {
        AVLNode<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * 双旋转二叉树节点: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     * 先右旋,再左旋
     */
    private AVLNode<E> doubleWithLeftChild(AVLNode<E> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     * 先左旋,再右旋
     */
    private AVLNode<E> doubleWithRightChild(AVLNode<E> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }


    /**
     * 返回某个节点的高度,如果该节点为null,返回-1
     *
     * @param node
     * @return
     */
    private int height(AVLNode<E> node) {
        return node == null ? -1 : node.getHeight();
    }

    /**
     * 查找node节点下最小
     *
     * @param node
     * @return
     */
    public AVLNode<E> findMin(AVLNode node) {

        if (node.left != null) {
            return findMin(node.left);
        } else {
            return node;
        }
    }


    public static void main(String[] args) {


        final AVLTree<Integer> avlTree = new AVLTree<>();



        // 原始数据:45, 12, 38, 49, 10, 37, 9, 19, 22, 83, 67, 70, 11

        // 5,10,85,15,70,90,40,55,20,60,30,50,65,80
        // 不同的插入序列,对平衡二叉树有影响吗?
        // todo 2019年6月25日20:36:37
        // TODO 经验证,同一组数据,不同的插入顺序,会产生不同的平衡二叉树
        // todo 这一点,二叉搜索树,二叉平衡树和红黑树,都有相同的性质
        // 85,55,20,50,65,80,5,10,15,60,30,70,90,40,

        Arrays.stream(new Integer[]{ 85,55,20,50,65,80,5,10,15,60,30,70,90,40})
                .forEach(e -> avlTree.insert(e));


        System.err.println(avlTree);


    }


    public static class AVLNode<E> {

        private E key;
        private AVLNode<E> left;
        private AVLNode<E> right;
        private int height;

        public AVLNode(E key) {
            this(key, null, null);
        }

        public AVLNode(E key, AVLNode<E> left, AVLNode<E> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public AVLNode<E> getLeft() {
            return left;
        }

        public void setLeft(AVLNode<E> left) {
            this.left = left;
        }

        public AVLNode<E> getRight() {
            return right;
        }

        public void setRight(AVLNode<E> right) {
            this.right = right;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
