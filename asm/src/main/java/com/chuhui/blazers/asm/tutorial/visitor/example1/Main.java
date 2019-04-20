package com.chuhui.blazers.asm.tutorial.visitor.example1;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Makeing root  entries....");

        Directory rootDir = new Directory("root");
        Directory binDir = new Directory("bin");
        Directory tmpDir = new Directory("tmp");
        Directory usrDir = new Directory("usr");

        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);

        binDir.add(new File("vim",1000));
        binDir.add(new File("latex",20000));
        rootDir.accept(new ListVisitor());






    }

}
