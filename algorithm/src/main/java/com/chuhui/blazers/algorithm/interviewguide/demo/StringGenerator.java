package com.chuhui.blazers.algorithm.interviewguide.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/18 18:30
 * Description:字符串产生器
 */
public class StringGenerator extends  NumberGenerator{


    public static void main(String[] args) {

        try {
            System.err.println("start execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(new File("D:\\demoFile\\randomStr.txt")), "utf-8");


            String [] resources=new String[]{"png","html","jpg","mp3","js","css"};
            char[] resourceStr=new char[]{'1',
                    '2',
                    '3',
                    '4',
                    '5',
                    '6',
                    '7',
                    '8',
                    '9',
                    '0',
                    'q',
                    'w',
                    'e',
                    'r',
                    't',
                    'y',
                    'u',
                    'i',
                    'o',
                    'p',
                    'a',
                    's',
                    'd',
                    'f',
                    'g',
                    'h',
                    'j',
                    'k',
                    'l',
                    'z',
                    'x',
                    'c',
                    'v',
                    'b',
                    'n',
                    'm',
                    'Q',
                    'W',
                    'E',
                    'R',
                    'T',
                    'Y',
                    'U',
                    'I',
                    'O',
                    'P',
                    'A',
                    'S',
                    'D',
                    'F',
                    'G',
                    'H',
                    'J',
                    'K',
                    'L',
                    'Z',
                    'X',
                    'C',
                    'V',
                    'B',
                    'N',
                    'M'
            };


            while (count.get()<NUMBER_FLAG){


                Random random=new Random();
                int bodyLength = random.nextInt(90)+10;

                int resourceType = random.nextInt(resources.length-1);


                StringBuffer buffer=new StringBuffer();
                for(int i=0;i<bodyLength;i++)
                    buffer.append(resourceStr[random.nextInt(resourceStr.length-1)]);

                buffer.append("."+resources[resourceType]);

                outputStream.write(buffer.toString()+"\n");
                count.incrementAndGet();
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("end execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


    }


}
