package com.chuhui.blazers.springexample.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * factory
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */

public class CustomDataSessionFactoryBean implements FactoryBean<CustomDataSessionFactory> {

    private String url;
    private String password;
    private String user;

    public CustomDataSessionFactoryBean(String url,String password,String user){
        this.url=url;
        this.password=password;
        this.user=user;
    }

    @Override
    public CustomDataSessionFactory getObject() throws Exception {
        return new CustomDataSessionFactory(url,password,user);
    }

    @Override
    public Class<?> getObjectType() {
        return CustomDataSessionFactory.class;
    }

    public void customMethod(){
        System.err.println("this is FactoryBeanService's custom Method");
    }

}
