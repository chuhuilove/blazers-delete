package com.chuhui.blazers.algorithm;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.Arrays;

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
     */

    private AVLNode<E> root;

    private static final int ALLOWED_IMBALANCE=1;

    /**
     * 插入节点
     *
     * @param key
     */
    public void insert(E key) {
        root = insert(key, root);
    }

    private AVLNode<E> insert(E key, AVLNode<E> node) {
        if(node==null){
            return new AVLNode<>(key);
        }

        int compareResult=key.compareTo(node.getKey());

        if(compareResult<0){
            node.setLeft(insert(key,node.getLeft()));
        }else if(compareResult>0){
            node.setRight(insert(key,node.getRight()));
        }else{

        }

        return balance(node);
    }

    private AVLNode<E> balance(AVLNode<E> node){
        if(node==null){
            return node;
        }


        if(height(node.getLeft())-height(node.getRight())>ALLOWED_IMBALANCE){

            if(height(node.getLeft().getLeft())>=height(node.getLeft().getRight())){
                node=rotateWithLeftChild(node);
            }else{
                node=doubleWithLeftChild(node);
            }

        }else{
            if(height(node.getRight())-height(node.getLeft())>ALLOWED_IMBALANCE){
                if(height(node.getRight().getRight())>=height(node.getRight().getLeft())){
                    node=rotateWithLeftChild(node);
                }else{
                    node=doubleWithLeftChild(node);
                }
            }
        }
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()))+1);
        return node;
    }


    private AVLNode<E> rotateWithLeftChild(AVLNode<E> node){
        AVLNode<E> node1=node.getLeft();
        node.setLeft(node1.getRight());
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()))+1);
        node1.setHeight(Math.max(height(node1.getLeft()),node.height)+1);
        return node1;
    }
    private AVLNode<E> doubleWithLeftChild(AVLNode<E> node){
        node.setLeft(rotateWithLeftChild(node.getLeft()));
        return rotateWithLeftChild(node);
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


    public static void main(String[] args) {


       final  AVLTree<Integer> avlTree=new AVLTree<>();

        Arrays.stream(new Integer[]{2,3,4,25,65,1,45,78,32,15,23,14})
                .forEach(e->avlTree.insert(e));


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
