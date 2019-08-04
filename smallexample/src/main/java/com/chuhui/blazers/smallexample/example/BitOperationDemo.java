package com.chuhui.blazers.smallexample.example;


import java.util.concurrent.atomic.AtomicInteger;

public class BitOperationDemo {



    public   final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits 运行状态存储在高位
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }



    public static void main(String[] args) {

        BitOperationDemo demo = new BitOperationDemo();

        int c = demo.ctl.get();
        int coreSize = workerCountOf(c);

        System.err.println(coreSize);


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
