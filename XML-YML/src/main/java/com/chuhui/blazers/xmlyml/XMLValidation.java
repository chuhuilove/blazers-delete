package com.chuhui.blazers.xmlyml;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/28 16:28
 * Description: dom4j xml 验证工具
 * From: http://www.voidcn.com/article/p-casmrnwr-rq.html
 */
public class XMLValidation {

    /**
     * DTD形式验证
     */
    public static final int Monde_DTD = 0;

    /**
     * Schema验证
     */
    public static final int Monde_Schema = 1;

    /**
     * @param xmlFile        xml文件路径
     * @param validationFile 校验文件路径
     * @param monde          校验类型
     * @return 校验是否成功
     */
    public static boolean testXMLFile(String xmlFile, String validationFile,
                                      int monde) {
        boolean flag = true;
        // 如果是dtd校验调用校验dtd的方法
        if (monde == Monde_DTD) {
            flag = testXMLByDTD(xmlFile, validationFile);
            // 如果是xsd校验调用校验xsd的方法
        } else if (monde == Monde_Schema) {
            flag = testXMLByXsd(xmlFile, validationFile);
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 校验 dtd 的方法
     *
     * @param xmlFile        xml文件路径
     * @param validationFile 校验文件路径
     * @return 校验是否成功
     */
    private static boolean testXMLByDTD(final String xmlFile,
                                        final String validationFile) {
        /*
         * 此类实体包括在 DTD 内引用的外部 DTD
         * 子集和外部参数实体（无论哪种情形，仅在在解析器都读取外部参数实体时）和在文档元素内引用的外部通用实体（如果解析器读取外部通用实体）
         */
        EntityResolver resolver = new EntityResolver() {// 应用程序解析外部实体
            public InputSource resolveEntity(String publicId, String systemId) {
                InputStream is = null;
                try {
                    is = new FileInputStream(validationFile);// 读取dtd文档
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
                InputSource ins = new InputSource(is);
                ins.setPublicId(publicId);
                ins.setSystemId(systemId);
                return ins;// 返回 InputSource实例
            }
        };
        SAXReader reader = new SAXReader(true);
        reader.setEntityResolver(resolver); // 向SAX 驱动器注册一EntityResolver个实例。
        boolean flag = validate(xmlFile, reader);// 调用验证方法
        return flag;
    }

    /**
     * 验证 xsd 方法
     *
     * @param xmlFile        xml文件路径
     * @param validationFile 校验文件路径
     * @return 校验是否成功
     */
    private static boolean testXMLByXsd(final String xmlFile, final String validationFile) {
        SAXReader reader = new SAXReader(true);// 创建SAXReader对象并制定需要验证
        // 也可通过reader.setValidation(true)来指定
        try {
            reader.setFeature("http://xml.org/sax/features/validation", true);// 设置功能标志的值name -功能名称，它是一个完全限定 URI。value - 请求的功能值（true 或false）。
            reader.setFeature("http://apache.org/xml/features/validation/schema", true);
            reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
            reader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", validationFile);// 设置属性的值 name - 属性名称，它是一个完全限定 URI。value - 请求的属性值
        } catch (SAXException e) {
            e.printStackTrace();
            return false;// 如果捕获异常 则返回false
        }
        boolean flag = validate(xmlFile, reader);// 调用验证方法
        return flag;
    }

    /**
     * @param xmlFile xml文件路径
     * @param reader  SAXReader 对象
     * @return 校验是否成功
     */
    private static boolean validate(final String xmlFile, final SAXReader reader) {
        XMLErrorHandler errorHandle = new XMLErrorHandler();// 错误处理类实例
        reader.setErrorHandler(errorHandle);// 向 XML 阅读器注册一个实例
        File file = new File(xmlFile);
        InputStream is = null;
        if (file.exists() && file.isFile()) {
            try {
                is = new FileInputStream(file);// 读取xml
                InputStreamReader in = new InputStreamReader(is, "utf-8");
                reader.read(in);
            } catch (FileNotFoundException e) {// 如果出现异常返回false
                e.printStackTrace();
                return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            } catch (DocumentException e) {
                e.printStackTrace();
                return false;
            }
        } else
            return false;
        if (errorHandle.getErrors().hasContent()) {// 如果错误处理类实例中包含错误信息返回false;
            return false;
        }
        return true;
    }

}
