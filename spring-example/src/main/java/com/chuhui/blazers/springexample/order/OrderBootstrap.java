package com.chuhui.blazers.springexample.order;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * FactoryBeanBootstrap
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */
public class OrderBootstrap {

    public static void main(String[] args) {

        final String packageName = "com.chuhui.blazers.springexample.order";
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(packageName);
        context.refresh();




    }

}
