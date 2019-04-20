package com.chuhui.blazers.asm.tutorial.visitor.example2;

import java.util.Objects;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class Main {

    public static void main(String[] args) {


        ObjectStructure os=new ObjectStructure();
        os.addElement(new ConcreteElementA());
        os.addElement(new ConcreteElementB());
        os.accept(new ConcreteVisitor1());
        os.accept(new ConcreteVisitor2());
    }

}
