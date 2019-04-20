package com.chuhui.blazers.asm.tutorial.visitor.example2;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class ConcreteVisitor2 extends Visitor {
    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {

        System.err.println("visitor2中的visit element A");

    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.err.println("visitor2中的visit element B");
    }
}
