package com.chuhui.blazers.algorithm.binaryTree;

public class BTTest {
    public static void main(String[] args) {
        BTreeMethod method = new BTreeMethod();
        TreeNode<String> node = new TreeNode<>();
        method.createTree(node);
        System.out.println("main");
        method.prinTree(node);
    }
}
