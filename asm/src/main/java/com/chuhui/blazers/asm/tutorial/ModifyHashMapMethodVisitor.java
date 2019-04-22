package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
public class ModifyHashMapMethodVisitor extends MethodVisitor implements Opcodes{

    public ModifyHashMapMethodVisitor(MethodVisitor mv) {
        super(ASM5,mv);
    }

    @Override
    public void visitCode(){
        //此方法在访问方法的头部时被访问到，仅被访问一次
        //此处可插入新的指令
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode){
        //此方法可以获取方法中每一条指令的操作类型，被访问多次
        //如应在方法结尾处添加新指令，则应判断：
        if(opcode == Opcodes.IRETURN){
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "hashCode", "()I", false);
        }
        super.visitInsn(opcode);
    }

}
