package com.chuhui.blazers.dyproxy.staticproxy.aggregation;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * UserServiceTimeImpl
 * 添加执行时间
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserServiceTimeImpl implements UserService {

    private UserService service;

    public UserServiceTimeImpl(UserService service) {
        this.service = service;
    }

    @Override
    public void addUser(String id) {

        String startTime = returnCurrentTimeFormated(commonlyUserDateTimeFormat);

        System.err.println("start execute saveOrUpdate function, currentTime: " + startTime);
        service.addUser(id);

        String endTime = returnCurrentTimeFormated(commonlyUserDateTimeFormat);
        System.err.println("end execute saveOrUpdate function, currentTime: " + endTime);

    }
}
