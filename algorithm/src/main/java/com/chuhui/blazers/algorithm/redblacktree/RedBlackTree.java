package com.chuhui.blazers.algorithm.redblacktree;


/**
 * 红黑树
 * <p>
 * 我辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 * <p>
 * 红黑树,首先是一棵二叉查找树,紧接着是一棵二叉平衡树.其具有以下性质:
 * 1. 每一个节点或者是黑色,或者是红色
 * 2. 根节点是黑色的
 * 3. 如果一个节点是红色的,那么它的子节点必须是黑色的
 * 4. 从一个节点到一个null引用的每一条路径必须包含相同数目的的黑色节点
 *
 * @author 纯阳子
 */
public class RedBlackTree<E extends Comparable<? extends E>> {
    private ReadBlackNode<E> header;
    private ReadBlackNode<E> nullNode;
    static final int BLACK = 1;
    static final int RED = 0;


    public RedBlackTree(E key) {

        nullNode = new ReadBlackNode<>(null);
        nullNode.leftNode = nullNode;
        nullNode.rightNode = nullNode;

        header = new ReadBlackNode<>(null);
        header.leftNode = header;
        header.rightNode = header;
    }


    private ReadBlackNode rotate(E key,ReadBlackNode parent){

        //FIXME 待定
        return null;
    }


    public static class ReadBlackNode<E> {

        private E key;
        private ReadBlackNode<E> leftNode;
        private ReadBlackNode<E> rightNode;
        private int color;

        public ReadBlackNode(E key) {
            this(key, null, null);
        }

        public ReadBlackNode(E key, ReadBlackNode<E> leftNode, ReadBlackNode<E> rightNode) {
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.color = BLACK;
        }

        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public ReadBlackNode<E> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(ReadBlackNode<E> leftNode) {
            this.leftNode = leftNode;
        }

        public ReadBlackNode<E> getRightNode() {
            return rightNode;
        }

        public void setRightNode(ReadBlackNode<E> rightNode) {
            this.rightNode = rightNode;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }


}
