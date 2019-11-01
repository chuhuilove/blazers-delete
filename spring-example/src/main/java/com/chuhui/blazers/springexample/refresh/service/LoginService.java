package com.chuhui.blazers.springexample.refresh.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * LoginService
 *
 * @author: cyzi
 * @Date: 2019/11/1 0001
 * @Description:TODO
 */
@Service
public class LoginService implements BeanFactoryPostProcessor {

    @Autowired
    private IndexService service;

    public String getIndexService() {
        System.err.println(getClass().getTypeName());
        return getClass().getSimpleName();
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.err.println("这是bean工厂的扩展点");

    }
}
