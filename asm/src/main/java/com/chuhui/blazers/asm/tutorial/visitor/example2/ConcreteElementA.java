package com.chuhui.blazers.asm.tutorial.visitor.example2;

import com.chuhui.blazers.asm.tutorial.visitor.example1.*;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class ConcreteElementA extends Element {
    @Override
    public void accecp(Visitor visitor) {

        visitor.visitConcreteElementA(this);

    }
}
