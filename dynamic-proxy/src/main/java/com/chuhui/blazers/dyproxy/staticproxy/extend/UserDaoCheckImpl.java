package com.chuhui.blazers.dyproxy.staticproxy.extend;

import java.text.MessageFormat;

/**
 * UserDoaCheckImpl
 *
 * 检查是否应该执行saveOrUpdate操作
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:
 */
public class UserDaoCheckImpl extends  UserDaoImpl{


    @Override
    public void saveOrUpdate(String id) {
        String format = MessageFormat.format( "Checks whether the function is executed, id:{0}", id);
        System.err.println(format);
        super.saveOrUpdate(id);
    }

}
