package com.chuhui.blazers.asm.tutorial.visitor.example1;

/**
 * Created by Administrator on 2019/4/18 0018.
 */
public class File extends Entry {

    private String name;
    private int size;


    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {

        System.out.println(prefix+"/"+this);

    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
