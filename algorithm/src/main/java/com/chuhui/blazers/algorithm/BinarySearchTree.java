package com.chuhui.blazers.algorithm;

import java.util.Arrays;

/**
 * 二叉搜索树
 *
 * @author: 纯阳子
 * @Date: 2019/4/30
 * @Description:TODO
 */
public class BinarySearchTree {


    //https://www.cnblogs.com/liwei2222/p/8013367.html 红黑树
    //https://www.cnblogs.com/mfrank/p/9227097.html hashmap和红黑树
    //https://blog.csdn.net/seagal890/article/details/79772239 二叉排序树

    private BinaryNode root;

    public BinarySearchTree() {
        root=null;
    }

    /**
     * 将树置为空
     */
    public void makeEmpty(){
        root=null;
    }


    /**
     * 是否为空树
     * @return
     */
    public boolean isEmpty(){
        return root==null;
    }


    /**
     * 如果在树中函数项key的节点,那么这个节点需要返回true,否则返回
     * @param key
     * @return
     */
    public boolean contains(int key){
        return contains(key,root);
    }

    private boolean contains(int key,BinaryNode node){

        if(node==null){
            return false;
        }

        if(node.getKey()>key){
            return contains(key,node.getlChild());
        }else if(node.getKey()<key){
            return contains(key,node.getrChild());
        }else{
            return true;
        }

    }


    /**
     * 获取树的最小节点
     * @return
     */
    public BinaryNode findMin(){
        if(isEmpty()){
            return null;
        }

        return findMin(root);

    }

    /**
     * 根据二叉搜索树的定义,左节点的肯定比父节点和右侧节点小
     * 所以递归寻找左侧的节点
     * @param node
     * @return
     */
    private BinaryNode findMin(BinaryNode node){

        if(node.getlChild()!=null){
            return findMin(node.getlChild());
        }else{
            return node;
        }
    }

    /**
     * 非递归,寻找最大的节点.
     * 根据二叉搜索树的定义,最大的节点在最右侧
     * 当节点是右节点,并且没有右孩子,该节点是树中最大的节点
     * @return
     */
    public BinaryNode findMax(){
        if(isEmpty()){
            return null;
        }
        return findMax(root);
    }
    private BinaryNode findMax(BinaryNode node){

        if(node!=null){

            while (node.getrChild()!=null){
                node=node.getrChild();
            }
        }
        return node;
    }


    public void remove(int key){

        root=remove(key,root);

    }

    private BinaryNode remove(int key, BinaryNode node) {


        /**
         * 如果是叶子节点,可以被立即删除,不受任何影响
         * 如果是拥有一个子节点, 将节点删除后,将其子节点整移动到删除的节点位置即可
         * 如果是拥有两个节点
         *
         */

        //FIXME 删除操作,需要理解一下
        if(node==null){
            return node;
        }

        if(node.getKey()>key){
            node.setlChild(remove(key,node.getlChild()));
        }else if(node.getKey()<key){
            node.setrChild(remove(key,node.getrChild()));
        }else if(node.getlChild()!=null && node.getrChild()!=null){
            //两个子节点
            node.setKey(findMin(node.getrChild()).getKey());
            node.setrChild(remove(node.getKey(),node.getrChild()));
        }else{
            node=(node.getlChild()!=null)? node.getlChild():node.getrChild();
        }
        return node;
    }


    /**
     * 插入节点
     * @param key
     */
    public void insert(int key){
        root=insert(key,root);
    }

    private BinaryNode insert(int key, BinaryNode node) {

        if(node==null){
            return new BinaryNode(key);
        }

        if(node.getKey()>key){
            node.setlChild(insert(key,node.getlChild()));
        }else if(node.getKey()<key){
            node.setrChild(insert(key,node.getrChild()));
        }else{

        }
        return node;
    }


    public BinaryNode getRoot() {
        return root;
    }

    public static void main(String[] args) {
        //二叉排序树极端情况下,会退化成链表
        //二叉排序树会不会成为链表,取决于root节点的值,若后续的节点的值,都小于root节点,且一个比一个小.
        //二叉排序树会退化成只有左子树的链表
        //若后续节点的都大于root节点,且一个比一个大,二叉排序树会退化成只有右子树的链表



        BinarySearchTree bst=new BinarySearchTree();

        Arrays.stream(new int[]{100,90,89,45,20,12,11,9,6,5})
                .forEach(e->bst.insert(e));


        System.err.println("min node:"+bst.findMin().getKey());

        System.err.println("max node:"+bst.findMax().getKey());

        bst.insert(13);
        bst.insert(-100);

        System.err.println("min node:"+bst.findMin().getKey());
        System.err.println("max node:"+bst.findMax().getKey());
    }


    public static class BinaryNode {

        private int key;
        private BinaryNode lChild;
        private BinaryNode rChild;


        public BinaryNode(int key) {
            this(key,null,null);

        }

        public BinaryNode(int key, BinaryNode lChild, BinaryNode rChild) {
            this.key = key;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public BinaryNode getlChild() {
            return lChild;
        }

        public void setlChild(BinaryNode lChild) {
            this.lChild = lChild;
        }

        public BinaryNode getrChild() {
            return rChild;
        }

        public void setrChild(BinaryNode rChild) {
            this.rChild = rChild;
        }
    }

}


