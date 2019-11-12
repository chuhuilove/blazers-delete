package com.chuhui.blazers.xmlyml;

import com.mysql.jdbc.jmx.LoadBalanceConnectionGroupManager;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/2/28 12:55
 * Description:dom4j 基础解析器
 */
public class Dom4JBasicParser {
    private String path;

    private Document document;

    Dom4JBasicParser() {
        path = getClass().getClassLoader().getResource("resources-dom4j.xml").getPath();

        setDocument();
    }

    void setDocument() {

        SAXReader reader = new SAXReader();
        try {
            document = reader.read(new File(path));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    Element getRootElement() {
        return document.getRootElement();
    }

    /**
     * 打印根元素属性
     *
     * @param rootElement 根元素
     */
    void printRootElementAttribute(Element rootElement) {
        Attribute namespace =
                rootElement.attribute("namespace");

        System.err.println("namespace:" + namespace.getValue());

        Attribute id = rootElement.attribute("id");
        System.err.println("id:" + id.getValue());

    }


    /**
     * 获取settings子节点下的node和属性
     *
     * @param rootElement
     */
    void prrintSettingNode(Element rootElement) {
        Element settingsElement = rootElement.element("settings");

        List childElements = settingsElement.elements();
        childElements.forEach(e -> {

            if (e instanceof Element) {
                Element settingElement = (Element) e;
                Attribute name = settingElement.attribute("name");
                Attribute value = settingElement.attribute("value");
                System.err.println("name:" + name.getValue() + " value:" + value.getValue());
            }
        });
    }

    /**
     * 打印mapper子节点下的node和属性
     *
     * @param rootElement
     */
    void pringMapperNode(Element rootElement) {

        if (rootElement.isRootElement()) {

            Element mappers = rootElement.element("mappers");

            List elements = mappers.elements();

            elements.forEach(e -> {

                Element mapper = (Element) e;
//                System.err.println(mapper.getQName().getName());// mappers下的子节点

                List attributes = mapper.attributes();
                attributes.forEach(attr -> {
                    Attribute mappAttr = (Attribute) attr;

                    //打印每一个属性值
                    System.err.println(mappAttr.getName() + "->" + mappAttr.getValue());

                    if (mappAttr.getName().equals("name")) {

                        //反射生成LoadBalanceConnectionGroupManager实例

                        try {
                            LoadBalanceConnectionGroupManager groupManager =
                                    (LoadBalanceConnectionGroupManager) Class.forName(mappAttr.getValue()).newInstance();

                            if (null != groupManager) {
                                System.err.println("实例已经生成....." + LocalDateTime.now().
                                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                            }

                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }

                });

            });

        }

    }

    public static void main(String[] args) {

        Dom4JBasicParser dom4jBasicParser = new Dom4JBasicParser();

        Element rootElement = dom4jBasicParser.getRootElement();

        dom4jBasicParser.printRootElementAttribute(rootElement);

        dom4jBasicParser.pringMapperNode(rootElement);
        dom4jBasicParser.prrintSettingNode(rootElement);

    }
}
