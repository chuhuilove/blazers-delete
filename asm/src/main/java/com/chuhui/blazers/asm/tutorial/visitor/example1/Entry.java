package com.chuhui.blazers.asm.tutorial.visitor.example1;

/**
 * Created by Administrator on 2019/4/18 0018.
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new FileTreatMentException();
    }

    protected abstract void printList(String prefix);

    @Override
    public String toString() {

        return getName()+" ("+getSize()+") ";

    }
}
