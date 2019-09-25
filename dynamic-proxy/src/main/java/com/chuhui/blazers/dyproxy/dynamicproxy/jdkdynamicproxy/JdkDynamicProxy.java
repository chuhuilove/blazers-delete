package com.chuhui.blazers.dyproxy.dynamicproxy.jdkdynamicproxy;

import com.chuhui.blazers.dyproxy.dynamicproxy.DynamicProxyService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JdkDynamicProxy
 *
 * jdk提供的动态代理示例
 *
 * @author: cyzi
 * @Date: 2019/9/25 0025
 * @Description:TODO
 */
public class JdkDynamicProxy {

    /**
     * 代理类生产器
     * @param target 代理类
     * @param handler 处理逻辑
     * @return
     */
    public static DynamicProxyService generator(final DynamicProxyService target, InvocationHandler handler) {
        System.err.println("debugger");
        System.err.println("debugger");
        System.err.println("debugger");
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        return (DynamicProxyService) o;
    }

    /**
     * 如果第二个参数小于等于0.则将第二个参数设置为5
     */
    public static class SetFiveForCount implements InvocationHandler {
        private DynamicProxyService target;

        public SetFiveForCount(DynamicProxyService target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Integer secondArg = (Integer) args[1];
            if (secondArg <= 0) {
                args[1] = 5;
            }
            return method.invoke(target, args);
        }
    }

    /**
     * 如果第二个参数大于100,则将该参数减去10
     */
    public static class SubtractTen implements InvocationHandler {
        private DynamicProxyService target;

        public SubtractTen(DynamicProxyService target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Integer secondArg = (Integer) args[1];
            if (secondArg >= 100) {
                args[1] = secondArg - 10;
            }
            return method.invoke(target, args);
        }
    }

}
