package com.chuhui.blazers.collection.map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/4/25 0025.
 */
public class CglibHashMap {


    public static CustomerHashMap getHashInstance(final CustomerHashMap map){

        Enhancer en=new Enhancer();

        en.setSuperclass(map.getClass());

//        en.setClassLoader(map.getClass().getClassLoader());
        en.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
                if("hash".equals(method.getName())){
                    System.err.println("拦截hash方法");
                    System.err.println("拦截hash方法");
                    System.err.println("拦截hash方法");
                }

                return method.invoke(map,args);
            }
        });


        return (CustomerHashMap) en.create();
    }


    public static void main(String[] args) {

//
//  HashMap<String,Integer> map=new HashMap<>();


        //asm 包冲突,无法运行,......
        //唯一暴力的方法.重写字节码
        //怎么解决类加载器的问题呢???

        CustomerHashMap<String,Integer> map=new CustomerHashMap<>();

        map=getHashInstance(map);

        for(int i=0;i<40;i++){
            map.put("xcc"+i,i);
        }
        System.err.println("");
    }


}
