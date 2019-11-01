package com.chuhui.blazers.springexample.refresh;

import com.chuhui.blazers.springexample.refresh.service.login.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ApplicationBootstrap
 *
 * @author: cyzi
 * @Date: 2019/11/1 0001
 * @Description:TODO
 */
public class ApplicationBootstrap {


    public static void main(String[] args) throws Exception {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(RefreshConfig.class);
        context.refresh();


        Object indexService = context.getBean("indexService");

        Method indesServiceMethod = ReflectionUtils.findMethod(indexService.getClass(), "getIndexService");
        indesServiceMethod.invoke(indexService);


//        context.register(IndexService.class);
        indexService = context.getBean("loginService");

        indesServiceMethod = ReflectionUtils.findMethod(indexService.getClass(), "getIndexService");
        indesServiceMethod.invoke(indexService);

    }
}


