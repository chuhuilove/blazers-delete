package com.chuhui.blazers.asm.tutorial.visitor.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/18 0018.
 */
public class Directory extends Entry {

    private String name;
    private List<Entry> directory=new ArrayList<>();

    public Directory(String name){
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {


        return 0;
    }

    @Override
    protected void printList(String prefix) {

    }
}
