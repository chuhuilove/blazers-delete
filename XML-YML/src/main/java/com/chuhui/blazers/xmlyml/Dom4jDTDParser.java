package com.chuhui.blazers.xmlyml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/28 13:58
 * Description:Dom4j DTD解析器
 */
public class Dom4jDTDParser {


    public static class Dom4jDTD1Parser {
        public static String XML_FILE_NAME = "dtd/resources-dom4j-dtd1.xml";
        public static String DTD_FILE_NAME = "dtd/resources-dom4j-DTD1.dtd";


        private Document document;

        public Dom4jDTD1Parser() {
            String xmlPath = getClass().getClassLoader().getResource(XML_FILE_NAME).getPath();
            String dtdPath = getClass().getClassLoader().getResource(DTD_FILE_NAME).getPath();
            SAXReader reader = new SAXReader();

            try {

                //校验DTD文件和xml文件是否一致
                if (XMLValidation.testXMLFile(xmlPath, dtdPath, XMLValidation.Monde_DTD)) {
                    document = reader.read(new File(xmlPath));
                } else {
                    throw new RuntimeException(XML_FILE_NAME + "和" + DTD_FILE_NAME + "不匹配");
                }

            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        public Document getDocument() {
            return document;
        }
    }


    public static class Dom4jDTD2Parser {

        public static String XML_FILE_NAME = "dtd/resources-dom4j-dtd2.xml";
        public static String DTD_FILE_NAME = "dtd/resources-dom4j-DTD2.dtd";


        private Document document;

        public Dom4jDTD2Parser() {
            String xmlPath = getClass().getClassLoader().getResource(XML_FILE_NAME).getPath();
            String dtdPath = getClass().getClassLoader().getResource(DTD_FILE_NAME).getPath();
            SAXReader reader = new SAXReader();

            try {

                //校验DTD文件和xml文件是否一致
                if (XMLValidation.testXMLFile(xmlPath, dtdPath, XMLValidation.Monde_DTD)) {
                    document = reader.read(new File(xmlPath));
                } else {
                    throw new RuntimeException(XML_FILE_NAME + "和" + DTD_FILE_NAME + "不匹配");
                }

            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        public Document getDocument() {
            return document;
        }
    }


    public static void main(String[] args) {
        testDTD1Parser();
        testDTD2Parser();
    }

    static void testDTD1Parser() {
        Dom4jDTD1Parser dom4jDTD1Parser = new Dom4jDTD1Parser();
        Element rootElement = dom4jDTD1Parser.getDocument().getRootElement();
        System.err.println("root element name" + rootElement.getName());

        List elements = rootElement.elements();

        elements.forEach(e -> {

            if (e instanceof Element) {
                Element element = (Element) e;
                System.err.println("标签:" + element.getName() + "的文本是:" + element.getText());
            }
        });
    }
    static void testDTD2Parser(){

        Dom4jDTD2Parser dom4jDTD2Parser = new Dom4jDTD2Parser();
        Element rootElement = dom4jDTD2Parser.getDocument().getRootElement();


        List attributes = rootElement.attributes();
        attributes.forEach(e->{
            if(e instanceof Attribute){
                Attribute rootAttr= (Attribute) e;
                System.err.println("root元素"+rootAttr.getName()+"属性的值为:"+rootAttr.getValue());
            }
        });


    }


}
