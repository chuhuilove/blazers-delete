package com.chuhui.blazers.collection.map;

import com.chuhui.blazers.asm.tutorial.CustomerClassLoader;
import com.chuhui.blazers.asm.tutorial.HashMapVisitorAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Map
 *
 * @author: 纯阳子
 * @Date: 2019/4/10
 * @Description:TODO
 */
public class MapTest {


    public static void testOrder(){

        /**
         * HashMap是无序的(插入顺序和自然顺序); 特别是,其顺序会随着时间的推移会自行改变(重新hash).
         */

        Map<String,Integer> map=new HashMap<>();

        System.err.println("start test Map.put");
        System.err.println("start test Map.put");
        System.err.println("start test Map.put");


        for(int i=1;i<=40;i++){
            map.put(i+"chuhui",1);
        }

        System.err.println("start test Map.put");
        System.err.println("start test Map.put");
        System.err.println("start test Map.put");

        map.keySet().stream().forEach(e-> System.err.println(e+"---->"+map.get(e)));


    }


    public static void customerMapTest() throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        /**
         * 无法修改父类的函数
         */


        CustomerHashMap orginMap=new CustomerHashMap();

        Method hashOrgin = orginMap.getClass().getSuperclass().getDeclaredMethod("hash", Object.class);
        hashOrgin.setAccessible(true);
        System.err.println("老的hash值:"+hashOrgin.invoke(orginMap,"xcc"));



        ClassReader cr = new ClassReader("com.chuhui.blazers.collection.map.CustomerHashMap");

        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

        HashMapVisitorAdapter hv = new HashMapVisitorAdapter(cw);
        cr.accept(hv, Opcodes.ASM5);

        byte[] bytes = cw.toByteArray();

        Class aClass = new CustomerClassLoader().defineClass("com.chuhui.blazers.collection.map.CustomerHashMap", bytes);

        // 无法进行转换,因为类加载器不一样
        // CustomerHashMap chm=(CustomerHashMap)aClass.newInstance();

        Object obj= aClass.newInstance();

        Method hash = obj.getClass().getSuperclass().getDeclaredMethod("hash", Object.class);
        hash.setAccessible(true);
        System.err.println("新的hash值:"+hash.invoke(obj,"xcc"));






    }



    public static void main(String[] args) {
        testOrder();
    }

}
