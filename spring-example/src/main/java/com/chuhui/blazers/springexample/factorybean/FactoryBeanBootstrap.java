package com.chuhui.blazers.springexample.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * FactoryBeanBootstrap
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */
public class FactoryBeanBootstrap {

    public static void main(String[] args) {

        final String packageName = "com.chuhui.blazers.springexample.factorybean";

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan(packageName);
        context.refresh();


        CustomDataSessionFactory bean = (CustomDataSessionFactory) context.getBean("sessionFactory");

        System.err.println(bean.getSesssion());
    }

}
