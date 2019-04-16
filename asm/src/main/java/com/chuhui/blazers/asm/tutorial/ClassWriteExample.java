package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by Administrator on 2019/4/16 0016.
 */
public class ClassWriteExample {

    public static byte[] classWriteTest() {


        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        cw.visit(V1_8, ACC_PUBLIC, "com/chuhui/blazers/asm/tutorial/ChuhuiHashMap",
                null, "java/lang/Object", null);


        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "FIRST_FILED", "I", null, new Integer(1)).visitEnd();


        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "SECOND_FILED", "I", null, new Integer(2)).visitEnd();

        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "THIRD_FILED", "I", null, new Integer(3)).visitEnd();

//        cw.visitField(ACC_PRIVATE, "customer", "Ljava/lang/String;", null, "this is customerValue").visitEnd();


//        cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null).visitEnd();
        cw.visitEnd();


        return cw.toByteArray();

    }


}
