package com.chuhui.blazers.dyproxy.dynamicproxy.util;

import org.apache.commons.io.IOUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * CustomeDynamicProxy
 * <p>
 * 手写动态代理
 *
 * @author: 纯阳子
 * @date: 2019/9/24
 */
public class CustomeDynamicProxy {

    /**
     * 换行符
     */
    static final String LINE_SEP = System.lineSeparator();
    /**
     * 制表符
     */
    static final String TABLE_SEP = "\t";

    /**
     * 生成的代理类名字
     */
    static final String PROXY_CLASS_NAME="CustomDynamicProxyServiceImpl";

    /**
     * 生成的代理类的存放位置
     */
    static final String JAVA_FILENAME = "D:\\com\\chuhui\\"+PROXY_CLASS_NAME+".java";



    static public Object proxyGenerator(Object target) {


        Class clazz=target.getClass().getInterfaces()[0];

        String packageName = "package com.chuhui;" + LINE_SEP;
        String importPackage = "import " +clazz.getName() + ";" + LINE_SEP;
        String declareClass = "public class "+PROXY_CLASS_NAME+" implements " + clazz.getSimpleName() + " {" + LINE_SEP;
        String declareFiled = TABLE_SEP + "private " + clazz.getSimpleName() + " service;" + LINE_SEP + LINE_SEP;

        // 构造函数
        String constructorMethod = TABLE_SEP + "public "+PROXY_CLASS_NAME+"(" + clazz.getSimpleName() + " service) {" + LINE_SEP
                + TABLE_SEP + TABLE_SEP + "this.service = service;" + LINE_SEP
                + TABLE_SEP + "}" + LINE_SEP + LINE_SEP;


        // 实现接口的方法
        StringBuilder methodBuilder = new StringBuilder();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            String overrideStr = TABLE_SEP + "@Override" + LINE_SEP;

            String methodName = declaredMethod.getName();
            String returnTypeName = declaredMethod.getReturnType().getSimpleName();

            StringBuilder methodDeclaredName = new StringBuilder(overrideStr + TABLE_SEP + "public " + returnTypeName + " " + methodName + "(");


            // 构建参数
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

            String noTypeParam = "";

            if (!(parameterTypes == null || parameterTypes.length <= 0)) {


                for (int i = 0; i < parameterTypes.length; i++) {

                    Class<?> parameterType = parameterTypes[i];

                    methodDeclaredName.append(parameterType.getSimpleName() + " arg" + i + ",");

                    noTypeParam += "arg" + i + ",";

                }

                methodDeclaredName.deleteCharAt(methodDeclaredName.length() - 1);
                noTypeParam = noTypeParam.substring(0, noTypeParam.length() - 1);


            }
            methodDeclaredName.append("){" + LINE_SEP + LINE_SEP);

            //下面是方法体

            if ("printParams".equals(methodName)) {
                // 拦截具体的方法名称

                String condi = TABLE_SEP + TABLE_SEP + "if(arg1<=0){" + LINE_SEP
                        + TABLE_SEP + TABLE_SEP + TABLE_SEP + "arg1=5;" + LINE_SEP
                        + TABLE_SEP + TABLE_SEP + "}" + LINE_SEP;
                methodDeclaredName.append(condi);
            }

            methodDeclaredName.append(TABLE_SEP + TABLE_SEP + "this.service." + methodName + "(" + noTypeParam + ");" + LINE_SEP);

            methodDeclaredName.append(TABLE_SEP + "}" + LINE_SEP);
            methodBuilder.append(methodDeclaredName);
        }


        String lastChar = "}" + LINE_SEP;

        String finalStr = packageName + importPackage + declareClass + declareFiled + constructorMethod + methodBuilder.toString() + lastChar;

        writeToFile(finalStr);


        // 将java文件编译成.class文件,然后通过类加载器加载进来

        try {

            // 编译java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

            Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(JAVA_FILENAME);

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
            task.call();
            fileManager.close();


            // 加载类
            URL[] urls = new URL[]{new URL("file:D:\\ \\\\\\")};

            URLClassLoader classLoader = new URLClassLoader(urls);

            Class<?> aClass = classLoader.loadClass("com.chuhui." + PROXY_CLASS_NAME);

            // 创建对象的三种方式
            //1.直接new
            //2.通过类对象的newInstance
            //3.通过类对象的constructor.newInstance
            Object constructor = aClass.getConstructor(clazz).newInstance(target);
            return constructor;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static void writeToFile(String context) {

        try {
            FileWriter fos = new FileWriter(JAVA_FILENAME);

            IOUtils.write(context, fos);

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
