package com.chuhui.blazers.dyproxy.staticproxy.extend;

import java.text.MessageFormat;

/**
 * UserDaoImpl
 *
 * UserDao的正常实现
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:
 */
public class UserDaoImpl implements UserDao{



    @Override
    public void saveOrUpdate(String id) {
        String format = MessageFormat.format("execute saveOrUpdate function, id:{0}", id);
        System.err.println(format);
    }
}
