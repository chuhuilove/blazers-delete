package com.chuhui.blazers.asm.tutorial.visitor.example1;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/18 0018.
 */
public class Directory extends Entry {

    private String name;
    private List<Entry> directory = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {

        if (CollectionUtils.isNotEmpty(directory)) {

            return directory.stream()
                    .map(Entry::getSize)
                    .reduce((a, b) -> a + b)
                    .get();

        }
        return 0;
    }


    @Override
    public Entry add(Entry entry) {
        directory.add(entry);
        return this;
    }

    @Override
    protected void printList(String prefix) {

        System.out.println(prefix + "/" + this);

        if (CollectionUtils.isNotEmpty(directory)) {
            directory.forEach(e -> e.printList(prefix + "/" + name));
        }

    }

}
