package com.chuhui.blazers.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimpleAgent {

    public static void premain(String params,Instrumentation inst){
        System.err.println("invoked SimpleAgent..."+params);

        inst.addTransformer(new SimplePreClassFileTransformer());


    }

}
