package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.ClassWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Administrator on 2019/4/16 0016.
 */
public class CustomerClassLoader extends ClassLoader {


    public Class defineClass(String name,byte[]b){
        return defineClass(name,b,0,b.length);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, IOException {


        //当前还没有造出一个方法的能力....
        //先这样凑活吧..

        byte[] chmBytes = ClassWriteExample.classWriteTest();
        final  Class aClass = new CustomerClassLoader().defineClass("com.chuhui.blazers.asm.tutorial.ChuhuiHashMap",chmBytes);



        Field[] fileds = aClass.getDeclaredFields();

        Arrays.stream(fileds).forEach((Field e) -> {
            try {

                System.err.println(e.getName() + "---->" + e.get(aClass));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });


        FileOutputStream fos = new FileOutputStream("ChuhuiHashMap.class");
        fos.write(chmBytes);
        fos.close();

//        chmBytes


//        Field first_filed = aClass.getField("FIRST_FILED");
//
//        Integer firstFiled = (Integer) first_filed.get(aClass);
//
//        System.err.println(firstFiled);





    }



}
