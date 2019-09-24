package com.chuhui.blazers.dyproxy.staticproxy.aggregation;

/**
 * UserServiceImpl
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public final class UserServiceImpl implements UserService {


    @Override
    public void addUser(String id) {
        System.err.println("this is UserServiceImpl's addUser method,id:"+id);
    }
}
