package com.chuhui.blazers.asm.tutorial.manipulationmethod;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class MethodChangeClassAdapter extends ClassVisitor implements Opcodes {
    public MethodChangeClassAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        if (cv != null) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {

        //此处的hash即为需要修改的方法  ，修改方法內容
        if("hash".equals(name)){
            //先得到原始的方法
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            MethodVisitor newMethod = null;
            //访问需要修改的方法
            newMethod = new AsmMethodVisit(mv);
            return newMethod;
        }
            return cv.visitMethod(access, name, desc, signature, exceptions);
    }
}
