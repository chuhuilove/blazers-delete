package com.chuhui.blazers.algorithm.interviewguide.chapter3;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 纯阳子
 * @Copyright (C) 2017-2018 Qy All rights reserved
 * @Date: 2019/3/6 20:40
 * @Description: 反序列化二叉树,
 * 递归和非递归方式进行先序,
 * 中序和后序遍历二叉树,
 * 层序序列化和反序列化二叉树
 */
public class BinaryTree {

    private final static String CLOSEING_TAHG = "#";


    //构造二叉树
    //递归方式先序中序后序遍历二叉树
    //非递归方式先序中序后序遍历二叉树
    //二叉树的序列化和反序列化


    /**
     * 通过先序遍历反序列化一棵二叉树
     *
     * @param preStr 解析的字符串
     * @return 二叉树的头结点
     * @see <a href="https://img-blog.csdnimg.cn/20190306122709931.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1poYW5MdW5IdWk=,size_16,color_FFFFFF,t_70"/>
     */
    public TreeNode reconByPreString(String preStr) {

        String[] splits = preStr.split("!");

        Queue<String> queue = new LinkedList<>();
        Arrays.asList(splits).forEach(e -> queue.offer(e));

        return reconPreOrder(queue);

    }

    private TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();

        if (CLOSEING_TAHG.equals(value)) {
            return null;
        }

        TreeNode header = new TreeNode(Integer.parseInt(value));

        header.setLeftNode(reconPreOrder(queue));
        header.setRightNode(reconPreOrder(queue));

        return header;
    }


    /**
     * 通过层序反序列化一棵二叉树
     *
     * @param preStr 解析的字符串
     * @return 二叉树的头结点
     */
    public TreeNode reconByLevelString(String preStr) {
        String[] splits = preStr.split("!");
        int index = 0;
        TreeNode header = generateNodeByString(splits[index++]);

        Queue<TreeNode> queue = new LinkedList<>();

        if (null != header) {
            queue.offer(header);
        }

        TreeNode node;

        while (!queue.isEmpty()) {
            node = queue.poll();
            node.setLeftNode(generateNodeByString(splits[index++]));
            node.setRightNode(generateNodeByString(splits[index++]));
            if (null != node.getLeftNode()) {
                queue.offer(node.getLeftNode());
            }
            if (null != node.getRightNode()) {
                queue.offer(node.getRightNode());
            }
        }
        return header;
    }

    private TreeNode generateNodeByString(String value) {

        if (CLOSEING_TAHG.equals(value)) {
            return null;
        }

        return new TreeNode(Integer.parseInt(value));
    }

    /**
     * 递归方式先序遍历一棵树
     *
     * @param rootNode 二叉树头结点
     */
    public void preOrderBinaryTree(TreeNode rootNode) {

        if (null != rootNode) {
            System.err.println(rootNode.getData());
            //先序遍历左子树
            preOrderBinaryTree(rootNode.getLeftNode());
            //先序遍历右子树
            preOrderBinaryTree(rootNode.getRightNode());
        }
    }

    /**
     * 递归方式中序遍历一棵树
     *
     * @param rootNode 二叉树头结点
     */
    public void inOrderBinaryTree(TreeNode rootNode) {

        if (null != rootNode) {
            //中序遍历左子树
            inOrderBinaryTree(rootNode.getLeftNode());
            System.err.println(rootNode.getData());
            //中序遍历右子树
            inOrderBinaryTree(rootNode.getRightNode());
        }
    }

    /**
     * 递归方式,后序遍历二叉树
     *
     * @param rootNode 二叉树头结点
     */
    public void postOrderBinaryTree(TreeNode rootNode) {

        if (null != rootNode) {
            //后序遍历左子树
            postOrderBinaryTree(rootNode.getLeftNode());
            //后序遍历右子树
            postOrderBinaryTree(rootNode.getRightNode());
            System.err.println(rootNode.getData());

        }

    }

    /**
     * 层序遍历二叉树
     *
     * @param rootNode 二叉树头结点
     */
    public void levelOrderBinaryTree(TreeNode rootNode) {


    }

    /**
     * 非递归方式先序遍历二叉树
     *
     * @param rootNode
     */
    public void nonRecursionPreOrderBinaryTree(TreeNode rootNode) {

        if (null != rootNode) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(rootNode);

            while (!stack.isEmpty()) {
                rootNode = stack.pop();

                System.err.println(rootNode.getData());
                if (null != rootNode.getRightNode()) {
                    stack.push(rootNode.getRightNode());
                }
                if (null != rootNode.getLeftNode()) {
                    stack.push(rootNode.getLeftNode());
                }
            }

        }

    }


    /**
     * 非递归方式中序遍历二叉树
     *
     * @param rootNode
     */
    public void nonRecursionInOrderBinaryTree(TreeNode rootNode) {

        if (null != rootNode) {
            Stack<TreeNode> stack = new Stack<>();


            while (!stack.isEmpty() || null != rootNode) {


                if (null != rootNode) {
                    stack.push(rootNode);
                    rootNode = rootNode.getLeftNode();
                } else {
                    rootNode = stack.pop();
                    System.err.println(rootNode.getData());
                    rootNode = rootNode.getRightNode();
                }

            }
        }
    }

    /**
     * 非递归方式后序遍历二叉树
     *
     * @param rootNode
     */
    public void nonRecursionPostOrderBinaryTree(TreeNode rootNode) {

    }


    public static class TreeNode {
        private TreeNode leftNode;
        private TreeNode rightNode;
        private Integer data;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public TreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }
    }

}
