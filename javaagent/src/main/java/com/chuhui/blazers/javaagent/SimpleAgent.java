package com.chuhui.blazers.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimpleAgent {

    public static void premain(String params, Instrumentation inst) {
        System.err.println("premain invoked SimpleAgent...start..." + params);
        inst.addTransformer(new SimplePreClassFileTransformer("premain"));

        Class[] loadedClasses = inst.getAllLoadedClasses();

        for (Class clazz : loadedClasses) {


            if (inst.isModifiableClass(clazz)) {
                System.err.println("premain " + clazz.getName() + " can modified!");
            } else {
                System.err.println("premain " + clazz.getName() + " can't modified!");
            }

        }
        System.err.println("premain invoked SimpleAgent..end....");

    }

    public static void agentmain(String params, Instrumentation inst) {
        System.err.println("agentmain invoked SimpleAgent..." + params);

        Class[] loadedClazz = inst.getAllLoadedClasses();

        for (Class clazz : loadedClazz) {
            if (inst.isModifiableClass(clazz)) {
                System.err.println(clazz.getName()+" can modifiable");
            }else{
                System.err.println(clazz.getName()+" can't modifiable");
            }
        }

        SimplePreClassFileTransformer transformer = new SimplePreClassFileTransformer("agentmain");
        inst.addTransformer(transformer);
    }


}
