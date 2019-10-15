package com.chuhui.blazers.springexample.webmvc;

import com.chuhui.blazers.springexample.webmvc.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * MyWebApplicationInitializer
 *
 * @author: cyzi
 * @Date: 2019/10/15 0015
 * @Description:TODO
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) throws ServletException {
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();


        DispatcherServlet servlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");

    }
}
