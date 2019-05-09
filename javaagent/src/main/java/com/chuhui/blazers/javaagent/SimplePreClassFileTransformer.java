package com.chuhui.blazers.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimplePreClassFileTransformer implements ClassFileTransformer {


//    private Predicate<String> pre

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.err.println("className<----->"+className);



        return null;
    }
}
