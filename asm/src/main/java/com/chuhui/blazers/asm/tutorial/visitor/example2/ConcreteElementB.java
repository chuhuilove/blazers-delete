package com.chuhui.blazers.asm.tutorial.visitor.example2;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class ConcreteElementB extends Element {
    @Override
    public void accecp(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
}
