package com.cuhui.blazers.jdbc.mybatisexample;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MybatisBootstrap
 *
 * @author: cyzi
 * @Date: 2019/10/14 0014
 * @Description:TODO
 */
public class MybatisBootstrap {


    public static void main(String[] args) throws IOException {
        String resource = "org/mybatis/builder/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        SqlSession sqlSession = factory.openSession();
        sqlSession.commit(true);
        sqlSession.delete("delete from tableName where id=100");
        sqlSession.close();
    }




}
