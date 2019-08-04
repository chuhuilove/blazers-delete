package com.chuhui.blazers.smallexample.example;

import java.util.Arrays;

/**
 * ArrayCopy
 * <p>
 * 数组使用System.arraycopy示例
 *
 * @author: 纯阳子
 * @Date: 2019/01/02
 * @Description:TODO
 */
public class ArrayCopy {

    public static void main(String[] args) {
        baseDataTest();
        objectDataTest();
        objectDataTest2();
    }


    static class ArrayCopyDataModel implements Cloneable {

        int age;

        public ArrayCopyDataModel(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ArrayCopyDataModel{" +
                    "age=" + age +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new ArrayCopyDataModel(age);
        }
    }


    /**
     * 基础数据类型复制.
     * <p>
     * 改变原始数字中的某一个元素的值,对新的数组无任何影响
     */
    static void baseDataTest() {

        int[] sourceArr = new int[]{150, 134, 145, 190, 200, 201};

        int[] targetArr = new int[sourceArr.length];

        System.arraycopy(sourceArr, 0, targetArr, 0, sourceArr.length);

        System.err.println("targetArr" + Arrays.toString(targetArr));
        System.err.println("sourceArr" + Arrays.toString(sourceArr));

        sourceArr[0] = 201;

        System.err.println("targetArr:" + Arrays.toString(targetArr));
        System.err.println("sourceArr:" + Arrays.toString(sourceArr));

    }

    /**
     * 采用for循环的方式进行复制对象数组.
     * <p>
     * 原始数组中的某一个元素内部的值被修改掉以后,对新的数组有影响,因为两个数组对应的下标相同的元素,是一个元素(相同的引用)
     */
    static void objectDataTest() {
        ArrayCopyDataModel[] sourceArr = new ArrayCopyDataModel[5];

        for (int i = 0; i < sourceArr.length; i++) {
            sourceArr[i] = new ArrayCopyDataModel(i + 1);
        }

        ArrayCopyDataModel[] targetArr = new ArrayCopyDataModel[sourceArr.length];

        for (int i = 0; i < sourceArr.length; i++) {

            targetArr[i] = sourceArr[i];
            /**
             * 因为ArrayCopy实现了Cloneable接口,重写了clone方法,做了一个深拷贝
             * 这里如果采用sourceArr[i].clone来进行赋值,是无法模拟出我们想要的效果.
             *
             * 因为sourceArr[i].clone返回的是一个新的对象.
             */
        }

        System.err.println("targetArr:" + Arrays.toString(targetArr));
        System.err.println("sourceArr:" + Arrays.toString(sourceArr));

        sourceArr[0].age = 100;

        System.err.println("targetArr:" + Arrays.toString(targetArr));
        System.err.println("sourceArr:" + Arrays.toString(sourceArr));

    }

    /**
     * 采用System.arraycopy的方式进行复制对象数组.
     * <p>
     * 原始数组中的某一个元素内部的值被修改掉以后,对新的数组有影响,因为两个数组对应的下标相同的元素,是一个元素(相同的引用).
     * <p>
     * 但是修改原始数组中某一个元素的值,即设置新的对象.对新的数组无影响.
     */
    static void objectDataTest2() {

        ArrayCopyDataModel[] sourceArr = new ArrayCopyDataModel[5];

        for (int i = 0; i < sourceArr.length; i++) {
            sourceArr[i] = new ArrayCopyDataModel(i + 1);
        }

        ArrayCopyDataModel[] targetArr = new ArrayCopyDataModel[sourceArr.length];

        System.arraycopy(sourceArr, 0, targetArr, 0, sourceArr.length);


        System.err.println("targetArr" + Arrays.toString(targetArr));
        System.err.println("sourceArr" + Arrays.toString(sourceArr));


        sourceArr[0].age = 100;

        System.err.println("targetArr" + Arrays.toString(targetArr));
        System.err.println("sourceArr" + Arrays.toString(sourceArr));

        sourceArr[0] = new ArrayCopyDataModel(1000);

        System.err.println("targetArr" + Arrays.toString(targetArr));
        System.err.println("sourceArr" + Arrays.toString(sourceArr));

    }


}
