package com.chuhui.blazers.dyproxy.staticproxy.aggregation;

/**
 * UserServiceTimeAutorImpl
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserServiceTimeAutorImpl implements UserService{

    private UserService service;

    public UserServiceTimeAutorImpl(UserService service) {
        this.service = service;
    }


    @Override
    public void addUser(String id) {
        service.addUser(id);
        System.err.println("获取权限");
    }
}
