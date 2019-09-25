package com.chuhui.blazers.dyproxy.dynamicproxy.util;

import java.lang.reflect.Method;

public interface CustomeInvokeHandler {

     Object invoke(Method method, Object[] args)
            throws Throwable;

}
