package com.chuhui.blazers.javaagent;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimpleAgent {

    public static void premain(String params, Instrumentation inst) {
        System.err.println("premain invoked SimpleAgent..." + params);
        inst.addTransformer(new SimplePreClassFileTransformer("premain"));
    }

    public static void agentmain(String params, Instrumentation inst) {
        System.err.println("agentmain invoked SimpleAgent..." + params);

        SimplePreClassFileTransformer transformer = new SimplePreClassFileTransformer("agentmain");

        inst.addTransformer(transformer);

        try {
            inst.redefineClasses(new ClassDefinition(transformer.getClassBeingRedefined(), transformer.getClassfileBuffer()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }

    }
}
