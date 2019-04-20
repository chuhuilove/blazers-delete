package com.chuhui.blazers.asm.tutorial.visitor.example1;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class ListVisitor extends Visitor {

    /**
     * 当前访问的文件夹的名字
     */
    private String currentdir = "";


    @Override
    public void visit(File file) {
        //在访问文件时被调用
        System.err.println(currentdir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {

        //访问文件夹时被调用

        System.err.println(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();

        if (CollectionUtils.isNotEmpty(directory.getDirectorys())) {
            directory.getDirectorys().forEach(e -> e.accept(this));
        }
        currentdir = savedir;


    }
}
