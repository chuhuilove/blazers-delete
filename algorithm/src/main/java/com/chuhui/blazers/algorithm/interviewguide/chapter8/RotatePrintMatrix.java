package com.chuhui.blazers.algorithm.interviewguide.chapter8;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/12 10:30
 * Description: 转圈打印矩阵
 */
public class RotatePrintMatrix {

    /**
     * 给定一个矩阵M :
     * 1, 2, 3, 4, 5
     * 6, 7, 8, 9, 0
     * 1, 2, 3, 4, 5
     * 6, 7, 8, 9, 0
     * 2, 3, 4, 5, 6
     * 3, 3, 4, 5, 10  (0,0)(5,4) (1,1)(4,3)
     * 其输出顺序为:1,2,3,4,5,1,6,2,1,9,8,7,2,6,1,2,1,5,4,3
     */


    static void printMatrix(int[][] mainMatrix) {

        int row = mainMatrix.length;  //矩阵的行
        int column = mainMatrix[0].length;  //矩阵的列

        //先获取矩阵的右下角的坐标 int [row-1][column-1]
        System.err.println(mainMatrix[row - 1][column - 1]);

        int rightRow = row - 1;
        int rightColumn = column - 1;

        int leftRow = 0;
        int leftColumn = 0;


        while (leftColumn <= rightColumn && leftRow <= rightRow) {

            print(mainMatrix, rightRow--, rightColumn--, leftRow++, leftColumn++);

        }

    }

    static void print(int[][] matrix, int rightRow, int rightColumn, int leftRow, int leftColumn) {

        if (leftRow == rightRow) { //只有一行
            for (int i = leftColumn; i <= rightColumn; i++) {
                System.out.print(matrix[leftRow][i] + " ");
            }
        } else if (leftColumn == rightColumn) {   //只有一列
            for (int i = leftRow; i <= rightRow; i++) {
                System.out.print(matrix[i][leftColumn] + " ");
            }

        } else {  //一般情况

            //todo 待定

        }


    }


    public static void main(String[] args) {


        int[][] mainMatrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 0}, {1, 2, 3, 4, 5}, {6, 7, 8, 9, 0}, {2, 3, 4, 5, 6}, {3, 3, 4, 5, 10}};

        RotatePrintMatrix.printMatrix(mainMatrix);
    }


}
