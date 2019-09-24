package com.chuhui.blazers.dyproxy.staticproxy.extend;

/**
 * UserDaoImpl
 * <p>
 * UserDao 添加权限
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:
 */
public class UserDaoAuthorityImpl extends UserDaoImpl {


    @Override
    public void saveOrUpdate(String id) {
        System.err.println("add Authority ");
        super.saveOrUpdate(id);
    }
}
