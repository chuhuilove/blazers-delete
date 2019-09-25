package com.chuhui.blazers.xmlyml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

import static com.chuhui.blazers.xmlyml.XMLValidation.Monde_Schema;
import static com.chuhui.blazers.xmlyml.XMLValidation.testXMLFile;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/28 17:21
 * Description:Dom4j XML Schema解析器
 */
public class Dom4jSchemaParser {

     static class Dom4jXSD1Parser{

           static String XML_FILE_NAME = "xsd/xsd-demo1.xml";
           static String DTD_FILE_NAME = "xsd/xsd-demo1.xsd";


         private Document document;

         public Dom4jXSD1Parser() {
             String xmlPath = getClass().getClassLoader().getResource(XML_FILE_NAME).getPath();
             String dtdPath = getClass().getClassLoader().getResource(DTD_FILE_NAME).getPath();
             SAXReader reader = new SAXReader();

             try {

                 //校验DTD文件和xml文件是否一致
                 if (testXMLFile(xmlPath, dtdPath,Monde_Schema)) {
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
        testXsd1Parser();

    }


    static void testXsd1Parser(){

        Dom4jXSD1Parser parser = new Dom4jXSD1Parser();
        Document document = parser.getDocument();
        Element rootElement = document.getRootElement();
        List elements = rootElement.elements();
        elements.forEach(e -> {

            if (e instanceof Element) {
                Element element = (Element) e;
                System.err.println("标签:" + element.getName() + "的文本是:" + element.getText());
            }
        });

    }



}
