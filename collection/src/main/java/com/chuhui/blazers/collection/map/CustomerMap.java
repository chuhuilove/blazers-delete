package com.chuhui.blazers.collection.map;

/**
 * CustomerList
 *
 * @author: 纯阳子
 * @Date: 2019/4/27
 * @Description:TODO
 */
public abstract class CustomerMap {

/**
 * //-javaagent:D:\MyLife\Code\person\java\javaagent\target\javaagent-1.0.jar
 */
    //重写final方法,怎么就那么难呢??


    /**
     * 原方法的结果是返回传过来的数字的2倍
     * 最后的结果,需要将其原数字返回
     *
     * @param num
     * @return
     */
   private static final int methodName(int num) {

        return num * 2;
    }


    public  abstract int dataGen(int num);

    protected final int getData(int num) {
        return methodName(num);
    }


}
