package com.chuhui.blazers.dyproxy.staticproxy.extend;

/**
 * UserDaoCheckAndAuthorAndTimeImpl
 *
 * 权限校验之后,检查库存,最后获取程序执行时间
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserDaoAuthorAndCheckAndTimeImpl extends UserDaoTimeImpl {

    @Override
    public void saveOrUpdate(String id) {

        System.err.println("权限校验");
        System.err.println("check 库存");
        super.saveOrUpdate(id);
    }
}
