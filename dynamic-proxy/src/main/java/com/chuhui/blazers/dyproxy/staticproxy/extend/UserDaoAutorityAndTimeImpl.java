package com.chuhui.blazers.dyproxy.staticproxy.extend;

/**
 * UserDaoAutorityAndTimeImpl
 *
 * UserDao实现,权限校验以后,获取执行时间
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserDaoAutorityAndTimeImpl extends UserDaoTimeImpl {




    @Override
    public void saveOrUpdate(String id) {
        System.err.println("添加权限校验");
        super.saveOrUpdate(id);
    }
}
