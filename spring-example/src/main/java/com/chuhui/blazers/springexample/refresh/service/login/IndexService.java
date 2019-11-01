package com.chuhui.blazers.springexample.refresh.service.login;

/**
 * IndexService
 *
 * @author: cyzi
 * @Date: 2019/11/1 0001
 * @Description:TODO
 */
public class IndexService {

    public String getIndexService() {

        System.err.println(IndexService.class.getTypeName());

        return getClass().getSimpleName();
    }

}
