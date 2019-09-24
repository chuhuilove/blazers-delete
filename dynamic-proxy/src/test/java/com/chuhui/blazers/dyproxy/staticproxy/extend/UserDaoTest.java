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

    static final String ID="cyzi";


    @Test
    public void saveOrUpdateCase(){
        UserDao dao=new UserDaoImpl();
        dao.saveOrUpdate(ID);
    }
}
