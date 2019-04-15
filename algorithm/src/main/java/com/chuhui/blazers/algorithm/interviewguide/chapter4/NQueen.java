package com.chuhui.blazers.algorithm.interviewguide.chapter4;

/**
 * NQueen
 *
 * @author: 纯阳子
 * @Date: 2019/4/10
 * @Description: N 皇后问题
 * @see <a href="http://note.youdao.com/noteshare?id=428a8e21ec0999cb9151e7fbb238b447&sub=3D204934D7EF44E98D263C6D8A4D82A7"/>
 */
public class NQueen {


    public static void main(String[] args) {

        System.err.println("8皇后" + new NQueen().num1(8));
        System.err.println("16皇后" + new NQueen().num1(16));
    }


    public int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    public int process1(int i, int[] record, int n) {


        if (i == n) {
            return 1;
        }
        int res = 0;

        for (int j = 0; j < n; j++) {

            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public boolean isValid(int[] record, int i, int j) {

        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


}
