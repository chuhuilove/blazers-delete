package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
public class HashMapVisitor  extends ClassVisitor{
    public HashMapVisitor( ) {
        super(ASM4);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        if("hash".equals(name)){
            System.err.println("进入hash方法了");
            return null;
        }else{
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new ModifyHashMapMethod(ASM4,mv);
        }
    }


    public static void main(String[] args) throws IOException {


        HashMapVisitor hmv = new HashMapVisitor();

        ClassReader reader=new ClassReader("java.util.HashMap");

        reader.accept(hmv,0);






    }


}


