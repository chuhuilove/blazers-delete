package com.chuhui.blazers.xmlyml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * ModifyXmlFile
 *
 * @author: cyzi
 * @Date: 2019/11/12 0012
 * @Description:TODO
 */
public class ModifyXmlFile {


    public static void main(String[] args) {

        String fileName = "ClusterConfig.xml";

        Document document = getDocument(fileName);
        Element rootElement = document.getRootElement();

        Element dsnInfo = rootElement.element("DsnInfo");



        Element timing = dsnInfo.element("Timing");
        timing.setText("this is test");


        OutputFormat compactFormat = OutputFormat.createPrettyPrint();
        compactFormat.setEncoding("UTF-8");

        String file = ModifyXmlFile.class.getClassLoader().getResource(fileName).getFile();

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(file), compactFormat);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    static Document getDocument(String fileName) {

        InputStream resourceAsStream =
                ModifyXmlFile.class.getClassLoader().getResourceAsStream(fileName);


        SAXReader reader = new SAXReader();
        try {
            return reader.read(resourceAsStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }


}
