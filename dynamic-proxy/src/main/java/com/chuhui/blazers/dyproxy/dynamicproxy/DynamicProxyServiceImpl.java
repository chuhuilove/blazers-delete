package com.chuhui.blazers.dyproxy.dynamicproxy;

/**
 * DynamicProxyServiceImpl
 *
 * DynamicProxyService默认实现
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class DynamicProxyServiceImpl implements DynamicProxyService{

    @Override
    public void printParams(String param1, Integer count) {
        if(count>0){
            for (int i = 0; i < count; i++) {
                System.err.println(param1+"--->"+i);
            }
        }

    }
}
