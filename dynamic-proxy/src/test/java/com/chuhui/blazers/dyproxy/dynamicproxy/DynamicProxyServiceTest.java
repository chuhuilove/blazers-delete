package com.chuhui.blazers.dyproxy.dynamicproxy;

import com.chuhui.blazers.dyproxy.dynamicproxy.jdkdynamicproxy.JdkDynamicProxy.SubtractTen;
import com.chuhui.blazers.dyproxy.dynamicproxy.jdkdynamicproxy.JdkDynamicProxy.SetFiveForCount;

import com.chuhui.blazers.dyproxy.dynamicproxy.util.CustomDynamicProxyVersion1;
import com.chuhui.blazers.dyproxy.dynamicproxy.util.CustomDynamicProxyVersion2;
import com.chuhui.blazers.dyproxy.dynamicproxy.util.CustomeInvokeHandler;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import sun.misc.ProxyGenerator;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.chuhui.blazers.dyproxy.dynamicproxy.jdkdynamicproxy.JdkDynamicProxy.generator;

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
     * 拦截printParams方法
     */
    @Test
    public void userJdkDynamicProxy() {

        DynamicProxyService dynamicProxyService = new DynamicProxyServiceImpl();
        DynamicProxyService setFiveForCountService = generator(dynamicProxyService, new SetFiveForCount(dynamicProxyService));
        System.err.println(setFiveForCountService);
        setFiveForCountService.printParams("cyzi", -1);
        DynamicProxyService subtractTenService = generator(dynamicProxyService, new SubtractTen(dynamicProxyService));
        subtractTenService.printParams("cyzi", 100);

    }


    @Test
    public void proxyGenerator1Case() {
        DynamicProxyService service = (DynamicProxyService) CustomDynamicProxyVersion1.proxyGenerator(new DynamicProxyServiceImpl());
        service.printParams("cyzi", 10);
    }

    @Test
    public void proxyGenerator2Case() {
        DynamicProxyService dynamicProxyService = new DynamicProxyServiceImpl();
        DynamicProxyService service = (DynamicProxyService) CustomDynamicProxyVersion2
                .proxyGenerator(DynamicProxyService.class, new CutomeInvokeHandlerImpl(dynamicProxyService));

        service.printParams("cyzi", 10);
    }


    /**
     * 验证jdk动态代理产生的代理类对象
     */
    @Test
    public void getJdkDynamicProxyBinaryStream() {

        byte[] bytes = ProxyGenerator.generateProxyClass(
                "customProxyName", new Class[]{DynamicProxyService.class}, 49);

        try {
            FileOutputStream fos = new FileOutputStream("D:\\customProxyName.class");
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * CustomeInvokeHandler的实现类,这里我们可以定义自己的处理逻辑
     */
    static class CutomeInvokeHandlerImpl implements CustomeInvokeHandler {

        private Object target;

        public CutomeInvokeHandlerImpl(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Method method, Object[] args) throws Throwable {
            System.err.println("自己的逻辑");
            System.err.println("自己的逻辑");
            return method.invoke(target, args);
        }
    }


}
