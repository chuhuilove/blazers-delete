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
 * CustomDynamicProxyVersion1
 * <p>
 * 手写动态代理 第一版
 *
 * @author: 纯阳子
 * @date: 2019/9/24
 */
public class CustomDynamicProxyVersion1 {

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
    static final String PROXY_CLASS_NAME = "CustomDynamicProxyServiceImpl";

    /**
     * 生成的代理类.java的存放位置
     */
    static final String JAVA_FILENAME = "D:\\com\\chuhui\\" + PROXY_CLASS_NAME + ".java";


    static public Object proxyGenerator(Object target) {

        /**
         * 0. 因为我们的的目标对象只实现了一个接口,所以这里只取第一个接口
         */
        Class clazz = target.getClass().getInterfaces()[0];

        /**
         * 1. 生成一些基本信息
         * 包声明
         * 导入
         * 类声明和私有属性
         */
        String packageName = "package com.chuhui;" + LINE_SEP;
        String importPackage = "import " + clazz.getName() + ";" + LINE_SEP;
        String declareClass = "public class " + PROXY_CLASS_NAME + " implements " + clazz.getSimpleName() + " {" + LINE_SEP;
        String declareFiled = TABLE_SEP + "private " + clazz.getSimpleName() + " service;" + LINE_SEP + LINE_SEP;

        /**
         * 2. 生成构造函数
         */
        String constructorMethod = TABLE_SEP + "public " + PROXY_CLASS_NAME + "(" + clazz.getSimpleName() + " service) {" + LINE_SEP
                + TABLE_SEP + TABLE_SEP + "this.service = service;" + LINE_SEP
                + TABLE_SEP + "}" + LINE_SEP + LINE_SEP;


        /**
         * 3. 实现目标对象的接口的方法,这里的接口,就是第0步获取的接口
         */
        StringBuilder methodBuilder = new StringBuilder();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            String overrideStr = TABLE_SEP + "@Override" + LINE_SEP;

            String methodName = declaredMethod.getName();
            String returnTypeName = declaredMethod.getReturnType().getSimpleName();
            StringBuilder methodDeclaredName = new StringBuilder(overrideStr + TABLE_SEP + "public " + returnTypeName + " " + methodName + "(");


            /**
             * 3.1 接口的方法,可能会有参数,获取参数的类型,以及给每个参数类型后面添加一个形参
             */
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

            String noTypeParam = "";

            if (parameterTypes.length > 0) {

                for (int i = 0; i < parameterTypes.length; i++) {

                    Class<?> parameterType = parameterTypes[i];
                    methodDeclaredName.append(parameterType.getSimpleName() + " arg" + i + ",");

                    noTypeParam += "arg" + i + ",";

                }

                methodDeclaredName.deleteCharAt(methodDeclaredName.length() - 1);
                noTypeParam = noTypeParam.substring(0, noTypeParam.length() - 1);
            }
            methodDeclaredName.append("){" + LINE_SEP + LINE_SEP);

            /**
             * 3.2 创建方法体,这里只是拦截一个名为printParams的方法,并判断其第二个参数是否小于零.
             *
             * 注意:这里属于我们自己要完成的逻辑,和目标对象无关
             *
             * 这里属于内嵌的逻辑,从另一个角度而言,亦属于硬编码,在第二版内,着重修改这一点
             *
             */
            if ("printParams".equals(methodName)) {
                // 拦截具体的方法名称

                String condi = TABLE_SEP + TABLE_SEP + "if(arg1<=0){" + LINE_SEP
                        + TABLE_SEP + TABLE_SEP + TABLE_SEP + "arg1=5;" + LINE_SEP
                        + TABLE_SEP + TABLE_SEP + "}" + LINE_SEP;
                methodDeclaredName.append(condi);
            }

            /**
             * 3.3 调用目标对象的方法
             */
            methodDeclaredName.append(TABLE_SEP + TABLE_SEP + "this.service." + methodName + "(" + noTypeParam + ");" + LINE_SEP);

            methodDeclaredName.append(TABLE_SEP + "}" + LINE_SEP);
            methodBuilder.append(methodDeclaredName);
        }


        String lastChar = "}" + LINE_SEP;

        String finalStr = packageName + importPackage + declareClass + declareFiled + constructorMethod + methodBuilder.toString() + lastChar;

        /**
         * 4. 将生成的描述代理类的字符串写到文件中
         *
         * 建议在这一步,debug一下,查看一下生产的.java文件,上面的一堆逻辑,就是为了产生这个.java文件
         */
        writeToFile(finalStr);

        try {

            /**
             * 5. 将.java文件编译成.class文件
             *
             * 这段code是从网络上抄来的..只是为了完成我们的功能,看看就行
             *
             */
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(JAVA_FILENAME);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
            task.call();
            fileManager.close();


            /**
             * 6. 将产生的.class文件加载到内存中
             */
            URL[] urls = new URL[]{new URL("file:D:\\ \\\\\\")};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> aClass = classLoader.loadClass("com.chuhui." + PROXY_CLASS_NAME);

            /**
             * 7. 通过反射的方式创建一个实例.
             *
             * 这里需要补充一个知识,在java中创建对象的方式,受眼界所限,目前为止,笔者所掌握的有三种:
             * 1. 直接new
             * 2. 通过类的类对象的newInstance方法
             * 3. 通过类的类对象的getConstructor().newInstance方法
             */
            Object constructor = aClass.getConstructor(clazz).newInstance(target);
            return constructor;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


     static void writeToFile(String context) {

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
