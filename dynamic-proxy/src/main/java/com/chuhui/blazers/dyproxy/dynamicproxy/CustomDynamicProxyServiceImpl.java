package com.chuhui.blazers.dyproxy.dynamicproxy;

import com.chuhui.blazers.dyproxy.dynamicproxy.util.CustomeInvokeHandler;

import java.lang.reflect.Method;

/**
 * CustomDynamicProxyServiceImpl
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/9/24
 */


public class CustomDynamicProxyServiceImpl implements DynamicProxyService {
    private DynamicProxyService service;
    private CustomeInvokeHandler handler;

    public CustomDynamicProxyServiceImpl(DynamicProxyService service, CustomeInvokeHandler handler) {
        this.service = service;
        this.handler = handler;
    }

    @Override
    public void printParams(String arg0, Integer arg1) {
        try {

            Method method = service.getClass().getDeclaredMethod("printParams", String.class, String.class);
            handler.invoke(method, new Object[]{arg0, arg1});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}

