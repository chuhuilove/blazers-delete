package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * MethodVisitorExample
 *
 * @author: 纯阳子
 * @Date: 2019/4/15
 * @Description:TODO
 */
public class ModifyHashMapMethod extends MethodVisitor {
    public ModifyHashMapMethod(int api,MethodVisitor mv) {
        super(api,mv);

    }

    @Override
    public void visitMaxs(int i, int i1) {
        super.visitMaxs(i, i1);
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
