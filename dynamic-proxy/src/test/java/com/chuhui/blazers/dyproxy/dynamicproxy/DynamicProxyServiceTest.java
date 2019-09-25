package com.chuhui.blazers.dyproxy.dynamicproxy;

import com.chuhui.blazers.dyproxy.dynamicproxy.util.CustomDynamicProxyVersion1;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyServiceTest
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class DynamicProxyServiceTest {


    /**
     * 使用jdk提供的动态代理
     * 拦截printParams方法,判断第二个参数,如果第二个参数小于或等于0,则将其重新赋值为5
     */
    @Test
    public void userJdkDynamicProxy() {
        DynamicProxyService service = generator(new DynamicProxyServiceImpl());
        service.printParams("cyzi", -1);
    }


    @Test
    public void proxyGeneratorCase() {
        DynamicProxyService service = (DynamicProxyService) CustomDynamicProxyVersion1.proxyGenerator(new DynamicProxyServiceImpl());
        service.printParams("cyzi", 10);
    }


    static DynamicProxyService generator(final DynamicProxyServiceImpl impl) {

        Object o = Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Integer secondArg = (Integer) args[1];
                if (secondArg <= 0) {
                    args[1] = 5;
                }
                return method.invoke(impl, args);
            }
        });

        return (DynamicProxyService) o;
    }


}
