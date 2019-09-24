package com.chuhui.blazers.dyproxy.staticproxy.extend;

import org.junit.Test;

/**
 * extend
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserDaoTest {

    static final String ID = "cyzi";


    @Test
    public void saveOrUpdateCase() {
        UserDao dao = new UserDaoImpl();
        dao.saveOrUpdate(ID);
    }

    @Test
    public void saveOrUpdateTimeCase() {
        UserDao dao = new UserDaoTimeImpl();
        dao.saveOrUpdate(ID);
    }

    @Test
    public void saveOrUpdateCheckCase() {
        UserDao dao = new UserDaoCheckImpl();
        dao.saveOrUpdate(ID);
    }

    /**
     * 权限校验之后,获取程序执行时间
     */
    @Test
    public void saveOrUpdateAutorityAndTimeCase() {
        UserDao dao = new UserDaoAutorityAndTimeImpl();
        dao.saveOrUpdate(ID);
    }

    /**
     * 权限校验以后,获取库存,再获取程序执行时间
     */
    @Test
    public void saveOrUpdateAuthorAndCheckAuthorAndTimeCase(){
        UserDao dao = new UserDaoAuthorAndCheckAndTimeImpl();
        dao.saveOrUpdate(ID);
    }



}
