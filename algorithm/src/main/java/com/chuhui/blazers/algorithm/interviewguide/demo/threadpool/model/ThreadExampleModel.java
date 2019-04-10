package com.chuhui.blazers.algorithm.interviewguide.demo.threadpool.model;

import java.util.Random;
import java.util.UUID;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/28 18:55
 * Description:TODO
 */
public class ThreadExampleModel {
    static Random random = new Random();
    private String name;
    private String address;
    private Integer age;
    private Integer pageIndex;

    public ThreadExampleModel(int pageIndex) {


        name = UUID.randomUUID().toString();
        address = UUID.randomUUID().toString();
        age = random.nextInt(100000) + 10;
        this.pageIndex = pageIndex;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
