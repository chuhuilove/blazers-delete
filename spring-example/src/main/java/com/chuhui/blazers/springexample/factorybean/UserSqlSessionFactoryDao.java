package com.chuhui.blazers.springexample.factorybean;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserSqlSessionFactory
 *
 * @author: cyzi
 * @Date: 2019/10/14 0014
 * @Description:TODO
 */
@Service
public class UserSqlSessionFactoryDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    public void deleteOne(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("delete from tableName where id=100");
        sqlSession.commit();
        sqlSession.close();
    }


}
