package com.chuhui.blazers.springexample.factorybean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomSessionFactory
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */

public class CustomDataSessionFactory {

    private String url;

    private String password;

    private String user;

    /**
     * 其他属性...
     */
    private List otherProperty;

    public CustomDataSessionFactory(String url, String password, String user) {
        this.url = url;
        this.password = password;
        this.user = user;

        // 其他属性初始化
    }

    public String getSesssion() {
        return url+password+user;
    }


}
