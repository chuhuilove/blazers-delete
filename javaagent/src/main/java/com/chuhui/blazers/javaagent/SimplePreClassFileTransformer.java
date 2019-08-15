package com.chuhui.blazers.javaagent;

import org.objectweb.asm.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


/**
 * Created by Administrator on 2019/4/28 0028.
 */
public class SimplePreClassFileTransformer implements ClassFileTransformer, Opcodes {


    private String type;

    public SimplePreClassFileTransformer(String type) {
        this.type = type;
    }


    private byte[] classfileBuffer;
    private Class<?> classBeingRedefined;


    public byte[] getClassfileBuffer() {
        return classfileBuffer;
    }


    public Class<?> getClassBeingRedefined() {
        return classBeingRedefined;
    }


    private static final String FILTER_CLASS_NAME = "com/chuhui/localtest/service/resinterface/insert/CustomerClass";


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        System.err.println(type + "<--->" + className);


//
//        if (FILTER_CLASS_NAME.equals(className)) {
//            classBeingRedefined=classBeingRedefined;
//
//
//            ClassReader reader = new ClassReader(classfileBuffer);
//            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
//
//            HashMapVisitorAdapter hv = new HashMapVisitorAdapter(writer);
//            reader.accept(hv, Opcodes.ASM5);
//
//            byte[] bytes = writer.toByteArray();
//
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream("CustomerMap.class");
//                fos.write(bytes);
//                fos.close();
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            classfileBuffer=bytes;
//            return bytes;
//        }

        return null;
    }

    protected class HashMapVisitorAdapter extends ClassVisitor {
        public HashMapVisitorAdapter(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

            //此处的hash即为需要修改的方法  ，修改方法內容
            if ("waitModifyMethodName".equals(name)) {

                //先得到原始的方法
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
                //访问需要修改的方法
                MethodVisitor newMethod = new ModifyHashMapMethodVisitor(mv);

                return newMethod;
            }
            return cv.visitMethod(access, name, desc, signature, exceptions);
        }


    }

    protected static class ModifyHashMapMethodVisitor extends MethodVisitor implements Opcodes {

        public ModifyHashMapMethodVisitor(MethodVisitor mv) {
            super(ASM5, mv);
        }

        @Override
        public void visitCode() {
            //此方法在访问方法的头部时被访问到，仅被访问一次
            //此处可插入新的指令
            super.visitCode();
        }

        @Override
        public void visitInsn(int opcode) {
            //此方法可以获取方法中每一条指令的操作类型，被访问多次
            //如应在方法结尾处添加新指令，则应判断：
            if (opcode == Opcodes.IRETURN) {
                mv.visitVarInsn(ILOAD, 0);
            }
            super.visitInsn(opcode);
        }

    }

}
