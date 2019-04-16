package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;


/**
 * TutorialTest
 *
 * @author: 纯阳子
 * @Date: 2019/4/12
 * @Description:TODO
 */
public class TutorialTest {


    public static void classPrinterTest() throws IOException {

        ClassPrinter cp = new ClassPrinter();

        //ClassReader可以看做是一个事件产生器
        ClassReader reader = new ClassReader("java.lang.Runnable");

        reader.accept(cp, 0);

    }

    public static void main(String[] args) throws IOException {


        classPrinterTest();

//        ClassPrinter cp = new ClassPrinter();
//
//        ClassReader reader = new ClassReader("java.util.HashMap");
//
//        reader.accept(cp,0);

//        cp.visitMethod()




    }




}
