package com.chuhui.blazers.collection.map;

import com.chuhui.blazers.asm.tutorial.ClassPrinter;
import com.chuhui.blazers.asm.tutorial.CustomerClassLoader;
import com.chuhui.blazers.asm.tutorial.HashMapVisitorAdapter;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import com.sun.glass.ui.Clipboard;
import org.apache.commons.collections4.CollectionUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import sun.security.ec.CurveDB;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Map
 *
 * @author: 纯阳子
 * @Date: 2019/4/10
 * @Description:TODO
 */
public class MapTest {


    public void testOrder() {

        /**
         * HashMap是无序的(插入顺序和自然顺序); 特别是,其顺序会随着时间的推移会自行改变(重新hash).
         */

        HashMap<String, Integer> map = new HashMap();

        map.put(UUID.randomUUID().toString(), 1);
        map.put(UUID.randomUUID().toString(), 2);
        map.put(UUID.randomUUID().toString(), 3);
        map.put(UUID.randomUUID().toString(), 4);
        map.put(UUID.randomUUID().toString(), 5);
        map.put(UUID.randomUUID().toString(), 6);
        map.put(UUID.randomUUID().toString(), 7);
        map.put(UUID.randomUUID().toString(), 8);
        map.put(UUID.randomUUID().toString(), 9);


        LinkedHashMap map1 = new LinkedHashMap(18);
        List<Integer> lists = new ArrayList<>();
        String list = new String("12321332132213213");
        HashMap map2 = new HashMap();


//        System.err.println(UUID.randomUUID().toString());

//        System.err.println(list.toString());

        for (int i = 1; i <= 40; i++) {
            map.put(i + "chuhui", 1);
            map1.put(i + "chuhui", 1);
            lists.add(i);
        }


        CglibHashMap hashMap = new CglibHashMap();

        ClassPrinter printer = new ClassPrinter();

        CollectionUtils.isNotEmpty(lists);

        Boolean b = new Boolean(true);


        Field[] fileds = hashMap.getClass().getDeclaredFields();
        System.err.println("fileds:" + fileds.getClass().getClassLoader());

        UUID.randomUUID().toString();
        System.err.println("UUID:" + UUID.class.getClassLoader());

        Method[] methods = hashMap.getClass().getDeclaredMethods();
        System.err.println("methods:" + methods.getClass().getClassLoader());
//        System.err.println(map);
//        System.err.println(map1);
    }


    public static void customerMapTest() throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        /**
         * 无法修改父类的函数
         */


        CustomerHashMap orginMap = new CustomerHashMap();

        Method hashOrgin = orginMap.getClass().getSuperclass().getDeclaredMethod("hash", Object.class);
        hashOrgin.setAccessible(true);
        System.err.println("老的hash值:" + hashOrgin.invoke(orginMap, "xcc"));


        ClassReader cr = new ClassReader("com.chuhui.blazers.collection.map.CustomerHashMap");

        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

        HashMapVisitorAdapter hv = new HashMapVisitorAdapter(cw);
        cr.accept(hv, Opcodes.ASM5);

        byte[] bytes = cw.toByteArray();

        Class aClass = new CustomerClassLoader().defineClass("com.chuhui.blazers.collection.map.CustomerHashMap", bytes);

        ClassLoader classLoader = CustomerHashMap.class.getClassLoader();
        Object obj = aClass.newInstance();
        ClassLoader classLoader1 = obj.getClass().getClassLoader();

        // 无法进行转换,因为类加载器不一样
        // CustomerHashMap chm=(CustomerHashMap)aClass.newInstance();


        ClassLoader classLoader3 = ClassWriter.class.getClassLoader();
        ClassLoader classLoader2 = MysqlClearPasswordPlugin.class.getClassLoader();
        ClassLoader classLoader4 = CollectionUtils.class.getClassLoader();

        ClassLoader classLoader5 = CurveDB.class.getClassLoader();

        ClassLoader classLoader6 = Clipboard.class.getClassLoader();

        Method hash = obj.getClass().getSuperclass().getDeclaredMethod("hash", Object.class);
        hash.setAccessible(true);
        System.err.println("新的hash值:" + hash.invoke(obj, "xcc"));


    }


    public static void main(String[] args) {


        //重写 final方法
//        for(;;){

            new MapTest().testOrder();
//            try {
//                Thread.sleep(100000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


    }

}
