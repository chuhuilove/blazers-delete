package com.chuhui.blazers.springexample.factorybean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.io.InputStream;

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
     * 若是同时开启,会产生两个BeanName相同的实例
     *
     *
     * @param property 连接属性
     * @return CustomDataSessionFactoryBean
     */
    @Bean("sessionFactory")
    public CustomDataSessionFactoryBean sessionFactoryBeanGenerator(ConnecProperty property){
        return  new CustomDataSessionFactoryBean(property.getUrl(),property.getPassword(),property.getUser());
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory createSqlsessionFactory(){
        String resource = "MinimalMapperConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        return factory;
    }

}
