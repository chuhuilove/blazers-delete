package com.chuhui.blazers.xmlyml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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
        EntityResolver resolver = new EntityResolver() {
            // 应用程序解析外部实体
            @Override
            public InputSource resolveEntity(String publicId, String systemId) {
                InputStream is = null;
                try {
                    // 读取dtd文档
                    is = new FileInputStream(validationFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
                InputSource ins = new InputSource(is);
                ins.setPublicId(publicId);
                ins.setSystemId(systemId);
                // 返回 InputSource实例
                return ins;
            }
        };
        SAXReader reader = new SAXReader(true);
        // 向SAX 驱动器注册一EntityResolver个实例。
        reader.setEntityResolver(resolver);
        // 调用验证方法
        boolean flag = validate(xmlFile, reader);
        return flag;
    }


    /**
     * 通过XSD（XML Schema）校验XML
     */
    /**
     * @param xmlFileName xml文件
     * @param xsdFileName xsd文件
     * @return
     * @from: https://blog.csdn.net/levelmini/article/details/50543759
     */
    public static boolean testXMLByXsd(String xmlFileName, String xsdFileName) {
        try {
            //创建默认的XML错误处理器
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            //获取基于 SAX 的解析器的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //解析器在解析时验证 XML 内容。
            factory.setValidating(true);
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。
            factory.setNamespaceAware(true);
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。
            SAXParser parser = factory.newSAXParser();
            //创建一个读取工具
            SAXReader xmlReader = new SAXReader();
            //获取要校验xml文档实例
            Document xmlDocument = (Document) xmlReader.read(new File(xmlFileName));
            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "file:" + xsdFileName);
            //创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(errorHandler);
            //校验
            validator.validate(xmlDocument);

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            //如果错误信息不为空，说明校验失败，打印错误信息
            if (errorHandler.getErrors().hasContent()) {
                writer.write(errorHandler.getErrors());
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("XML文件: " + xmlFileName + " 通过XSD文件:" + xsdFileName + "检验失败。\n原因： " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * @param xmlFile xml文件路径
     * @param reader  SAXReader 对象
     * @return 校验是否成功
     */
    private static boolean validate(final String xmlFile, final SAXReader reader) {
        // 错误处理类实例
        XMLErrorHandler errorHandle = new XMLErrorHandler();
        // 向 XML 阅读器注册一个实例
        reader.setErrorHandler(errorHandle);
        File file = new File(xmlFile);
        InputStream is = null;
        if (file.exists() && file.isFile()) {
            try {
                // 读取xml
                is = new FileInputStream(file);
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
        } else {
            return false;
        }

        if (errorHandle.getErrors().hasContent()) {
            // 如果错误处理类实例中包含错误信息返回false;
            return false;
        }
        return true;
    }


//     private boolean isXMLValid() {
//        boolean flag = true;
//        try {
//            Source schemaFile = new StreamSource(
//                    Thread. currentThread ().getContextClassLoader().getResourceAsStream( "schema/PassageReport.xsd" )
//
//            );
//          
//            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = factory.newSchema(schemaFile);
//            Validator validator = schema.newValidator();
//            validator.validate(new StreamSource(new StringReader(targetXML)));
//           
//        } catch (Exception e) {
//            flag = false;
//            logger.error(e.getMessage());
//        }
//
//        return flag;
//    }
//


}
