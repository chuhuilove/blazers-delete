package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
public class CreatClasaFiledMethod implements Opcodes {



    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, IOException, InvocationTargetException, NoSuchFieldException {

        byte[] classBytes = cretaeMethod();

        Class createClass = new CustomerClassLoader().defineClass("com.chuhui.blazers.asm.tutorial.CreateClass1", classBytes);

        Object newCreateClass = createClass.newInstance();


        Field firstFiled = createClass.getDeclaredField("firstFiled");

        firstFiled.setAccessible(true);
        System.err.println(firstFiled.get(newCreateClass));

        firstFiled.set(newCreateClass,1000);

        Method firstFiledMethod = createClass.getDeclaredMethod("getFirstFiled");

        System.err.println(firstFiledMethod.invoke(newCreateClass));



    }

    static byte[] cretaeMethod() throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //创建一个类
        cw.visit(V1_8, ACC_PUBLIC, "com/chuhui/blazers/asm/tutorial/CreateClass1", null, "java/lang/Object", null);


        /**
         * 添加构造函数
         */
        MethodVisitor init = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        init.hashCode();
        init.visitVarInsn(ALOAD, 0);
        init.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V", false);
        init.visitInsn(RETURN);
        init.visitMaxs(1, 1);
        init.visitEnd();


        /**
         * 添加一个方法
         *
         * public static final int hash(Object obj){
         *      return obj.hashCode();
         * }
         */

        MethodVisitor hash = cw.visitMethod(ACC_PUBLIC+ACC_FINAL+ACC_STATIC , "hash", "(Ljava/lang/Object;)I", null, null);
        //还需要定义函数体
        hash.visitCode();
        hash.visitVarInsn(ALOAD, 0);
        hash.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "hashCode", "()I", false);
        hash.visitInsn(IRETURN);
        hash.visitMaxs(1, 1);
        hash.visitEnd();


        /**
         * 添加一个字段 private int firstFiled;
         */
        cw.visitField(ACC_PRIVATE, "firstFiled", "I", null, null).visitEnd();

        /**
         * 添加一个方法
         * public int getFirstFiled(){
         *  return firstFiled;
         * }
         */
        MethodVisitor getFirstFiled = cw.visitMethod(ACC_PUBLIC, "getFirstFiled", "()I", null, null);
        getFirstFiled.hashCode();

        getFirstFiled.visitVarInsn(ALOAD, 0);
        getFirstFiled.visitFieldInsn(GETFIELD, "com/chuhui/blazers/asm/tutorial/CreateClass1", "firstFiled", "I");
        getFirstFiled.visitInsn(IRETURN);
        getFirstFiled.visitMaxs(1, 1);
        getFirstFiled.visitEnd();


        cw.visitEnd();




        byte[] bytes = cw.toByteArray();

        FileOutputStream fos = new FileOutputStream("CreateClass1.class");
        fos.write(bytes);
        fos.close();

        return bytes;


    }


}
