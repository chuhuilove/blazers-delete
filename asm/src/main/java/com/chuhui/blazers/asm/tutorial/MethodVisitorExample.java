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
public class MethodVisitorExample extends MethodVisitor {
    public MethodVisitorExample() {
        super(ASM4);
    }
}
