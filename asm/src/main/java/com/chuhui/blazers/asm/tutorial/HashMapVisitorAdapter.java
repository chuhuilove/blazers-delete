package com.chuhui.blazers.asm.tutorial;

import com.chuhui.blazers.asm.tutorial.examplemodel.FinalMethodModel;
import com.chuhui.blazers.asm.tutorial.manipulationmethod.AsmMethodVisit;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
public class HashMapVisitorAdapter extends ClassVisitor {
    public HashMapVisitorAdapter(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        //此处的hash即为需要修改的方法  ，修改方法內容
        if ("hash".equals(name)) {
            //先得到原始的方法
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            MethodVisitor newMethod = null;
            //访问需要修改的方法
            newMethod = new ModifyHashMapMethodVisitor(mv);
            return newMethod;
        }
        return cv.visitMethod(access, name, desc, signature, exceptions);
    }


    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        Method hash = FinalMethodModel.class.getDeclaredMethod("hash", Object.class);

        hash.setAccessible(true);
        System.err.println("原始模型类:"+ hash.invoke(FinalMethodModel.class, 1233));



        ClassReader cr = new ClassReader("com.chuhui.blazers.asm.tutorial.examplemodel.FinalMethodModel");

        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

        HashMapVisitorAdapter hv = new HashMapVisitorAdapter(cw);
        cr.accept(hv, Opcodes.ASM5);

        byte[] bytes = cw.toByteArray();




        //生成了
        Class aClass = new CustomerClassLoader().defineClass("com.chuhui.blazers.asm.tutorial.examplemodel.FinalMethodModel", bytes);
        Method newHash = aClass.getDeclaredMethod("hash", Object.class);

        newHash.setAccessible(true);
        System.err.println("改写后的值:"+ newHash.invoke(aClass, "xcc21312313"));


        System.err.println("HashMap:"+hash("xcc21312313"));



//        FileOutputStream fos = new FileOutputStream("ChuHuiHashMap.class");
//        fos.write(bytes);
//        fos.close();


    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}


