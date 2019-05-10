package com.chuhui.blazers.javaagent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimpleAgent {

    public static void premain(String params,Instrumentation inst){
        System.err.println("premain invoked SimpleAgent..."+params);
        inst.addTransformer(new SimplePreClassFileTransformer());
    }

    public static void agentmain(String params,Instrumentation inst){
        System.err.println("agentmain invoked SimpleAgent..."+params);

        SimplePreClassFileTransformer transformer = new SimplePreClassFileTransformer();
        inst.addTransformer(transformer);



    }
}
