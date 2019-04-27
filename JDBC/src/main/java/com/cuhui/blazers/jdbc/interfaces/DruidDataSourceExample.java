package com.cuhui.blazers.jdbc.interfaces;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.util.DruidPasswordCallback;
import com.mysql.jdbc.Driver;

import javax.security.auth.callback.PasswordCallback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * DruidDataSourceExample
 *
 * @author: 纯阳子
 * @Date: 2019/4/23
 * @Description:TODO
 */
public class DruidDataSourceExample {


    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl("jdbc:mysql://server.chuhui.com:3306/Student?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8");

        dataSource.setDriver(new Driver());

        dataSource.setUsername("root");

        dataSource.setPassword("ZXCvbnm123!@#");


        dataSource.setAsyncInit(false);

        dataSource.setMaxActive(12);
        dataSource.setInitialSize(10);
        dataSource.setLogAbandoned(true);
        dataSource.setRemoveAbandoned(true);


        DruidPooledConnection dpconnection = dataSource.getConnection();

        Connection connection = dpconnection.getConnection();

        PreparedStatement ps = connection.prepareStatement("insert into student(stu_name,score_yuwen,score_shuxue,score_yingyu,score_sum) values(?,?,?,?,?)");

        ps.setString(1, "xcc");
        ps.setFloat(2, Math.abs(ThreadLocalRandom.current().nextFloat()));
        ps.setFloat(3, Math.abs(ThreadLocalRandom.current().nextFloat()));
        ps.setFloat(4, Math.abs(ThreadLocalRandom.current().nextFloat()));
        ps.setString(5, "131125");


        ps.executeUpdate();



    }


}
