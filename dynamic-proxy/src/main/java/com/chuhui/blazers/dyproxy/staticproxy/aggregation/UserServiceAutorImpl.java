package com.chuhui.blazers.dyproxy.staticproxy.aggregation;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * UserServiceAutorImpl
 * 权限校验功能
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserServiceAutorImpl implements UserService {

    private UserService service;

    public UserServiceAutorImpl(UserService service) {
        this.service = service;
    }

    @Override
    public void addUser(String id) {
        System.err.println("添加权限校验");

        service.addUser(id);
    }
}
