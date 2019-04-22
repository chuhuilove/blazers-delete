package com.chuhui.blazers.asm.tutorial.manipulationmethod;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class AsmExample extends ClassLoader implements Opcodes {

    public static void main(String args[]) throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException{
        ClassReader cr = new ClassReader(HashMap.class.getName());



        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MethodChangeClassAdapter(cw);
        cr.accept(cv, Opcodes.ASM4);

        byte[] code = cw.toByteArray();


//        AsmExample loader = new AsmExample();
//        Class<?> exampleClass = loader.defineClass(Foo.class.getName(), code, 0, code.length);
//
//        for(Method method : exampleClass.getMethods()){
//            System.out.println(method);
//        }
//
//        System.out.println("***************************");
//
//        // uses the dynamically generated class to print 'Helloworld'
//        //調用changeMethodContent，修改方法內容
//        exampleClass.getMethods()[1].invoke(exampleClass.newInstance(), null);
//
//        System.out.println("***************************");
//        //調用execute,修改方法名
//        exampleClass.getMethods()[2].invoke(exampleClass.newInstance(), null);
//        // gets the bytecode of the Example class, and loads it dynamically
//
//        FileOutputStream fos = new FileOutputStream("Example.class");
//        fos.write(code);
//        fos.close();
    }

}
