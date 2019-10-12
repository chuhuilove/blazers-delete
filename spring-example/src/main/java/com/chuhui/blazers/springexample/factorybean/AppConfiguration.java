package com.chuhui.blazers.springexample.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * AppConfiguration
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */
@Configuration
//@ImportResource("classpath:factorybean.xml")
public class AppConfiguration {

    @Bean
    public ConnecProperty createConnectionProperty(){
        ConnecProperty connecProperty = new ConnecProperty();

        connecProperty.setPassword("passwd");
        connecProperty.setUrl("url");
        connecProperty.setUser("user");
        return connecProperty ;
    }

    /**
     * 这个方法和类上面@ImportResource("classpath:factorybean.xml")不能同时开启
     * @param property 连接属性
     * @return CustomDataSessionFactoryBean
     */
    @Bean("sessionFactory")
    public CustomDataSessionFactoryBean sessionFactoryBeanGenerator(ConnecProperty property){
        return  new CustomDataSessionFactoryBean(property.getUrl(),property.getPassword(),property.getUser());
    }

}
