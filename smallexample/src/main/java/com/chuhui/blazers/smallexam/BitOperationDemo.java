package com.chuhui.blazers.smallexam;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BitOperationDemo {


    public static void main(String[] args) {


    int hex=0x71;

        ByteBuffer byteBuffer = ByteBuffer.allocate(10).putInt(hex);

        byteBuffer.flip();
        System.err.println("biubiubiu");
        System.err.println("biubiubiu");

    }


    /**
     * 求某个数的二进制中,1的个数
     *
     * @param num
     * @return
     */
    public static int count1Num(int num) {

        int count = 0;
        while (num > 0) {
            num &= num - 1;
            count++;
        }
        return count;
    }


    /**
     * 判断某个数是不是2的幂次方
     *
     * @param num
     * @return
     */
    public static boolean check2(int num) {
        return (num & (num - 1)) == 0;
    }

    /**
     * 不采用第三个值,实现两个数字交换
     *
     * @param num1
     * @param num2
     */
    public static void swapNum(int num1, int num2) {

        System.err.println("start---->num1:" + num1 + ",num2:" + num2);
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;

        System.err.println("end---->num1:" + num1 + ",num2:" + num2);

    }


}
