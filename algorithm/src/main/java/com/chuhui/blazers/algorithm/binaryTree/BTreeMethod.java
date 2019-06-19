package com.chuhui.blazers.algorithm.binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BTreeMethod {


    /**
     * 先序创建树
     * @param node
     */
    public void createTree(TreeNode<String> node){
        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
            node.setData(inputStream.readLine());
            System.out.println("node.data:" + node.getData());
            if(node.getData().equals("#")){
                System.out.println("RETURN。。。");
               return;
            }

            System.out.println("继续了。。。");
            node.setLeftChild(new TreeNode<>());
            createTree(node.getLeftChild());
            if(node.getLeftChild().getData().equals("#")){
                node.setLeftChild(null);
            }

            node.setRightChild(new TreeNode<>());
            createTree(node.getRightChild());
            if(node.getRightChild().getData().equals("#")){
                node.setRightChild(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void preOrderRecur(TreeNode<String> node){
        if(node == null){
            return;
        }
        System.out.println(node.getData());
        preOrderRecur(node.getLeftChild());
        preOrderRecur(node.getRightChild());
    }

    public void prinTree(TreeNode<String> node){
        if(node == null){
            return;
        }
        prinTree(node.getLeftChild());
        System.out.println(node + " ");
        prinTree(node.getRightChild());
    }
}
