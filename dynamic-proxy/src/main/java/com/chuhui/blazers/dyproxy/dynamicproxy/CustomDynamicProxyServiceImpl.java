package com.chuhui.blazers.dyproxy.dynamicproxy;

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

    public CustomDynamicProxyServiceImpl(DynamicProxyService service) {
        this.service = service;
    }

    @Override
    public void printParams(String param1, Integer count) {
        if (count <= 0) {
            count = 5;
        }
        service.printParams(param1, count);
    }
}

