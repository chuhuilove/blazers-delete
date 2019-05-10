package com.chuhui.blazers.collection;

import com.chuhui.blazers.collection.map.CustomerMap;
import com.chuhui.blazers.collection.map.CustomerMapImpl;

import java.lang.reflect.Method;
import java.rmi.ServerError;
import java.util.Arrays;

/**
 * JavaAgentPremain
 *
 * @author: 纯阳子
 * @Date: 2019/5/9
 * @Description:TODO
 */
public class JavaAgentPremain {


    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(10000L);


        CustomerMap customerMap = new CustomerMapImpl();

        System.err.println("我在这里等着你回来....");
        System.err.println("old  value:" + customerMap.dataGen(30));


        Thread.sleep(10000L);
        System.err.println("我在这里等着你回来....");
        System.err.println("new   value:" + customerMap.dataGen(30));

        //final 已经搞定了...

//        -javaagent:D:\MyLife\Code\person\java\github\blazers-parent\javaagent\target\javaagent-1.0-SNAPSHOT.jar


    }

}
