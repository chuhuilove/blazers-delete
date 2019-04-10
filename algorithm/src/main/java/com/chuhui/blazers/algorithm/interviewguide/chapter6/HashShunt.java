package com.chuhui.blazers.algorithm.interviewguide.chapter6;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: 纯阳子
 * Date: 2019/1/18 16:36
 * Description:哈希分流
 */
public class HashShunt {


    static  final Integer SPLIT_COUNT=100;

    public static void main(String[] args) {

        System.err.println("start execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        try {
            FileReader fileReader=new FileReader(new File("D:\\demoFile\\randomStr.txt"));
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            BufferedWriter [] writers= new BufferedWriter[SPLIT_COUNT];

            for(int i=0;i<SPLIT_COUNT;i++){
                writers[i]=new BufferedWriter(new FileWriter(new File("D:\\demoFile\\hashshunt\\string"+i+".txt")));
            }

            String readStr;
            while(( readStr=bufferedReader.readLine())!=null){
                int num=Math.abs(readStr.hashCode())%SPLIT_COUNT;
                writers[num].write(readStr+"\n");
            }

            bufferedReader.close();

            for(int i=0;i<SPLIT_COUNT;i++){
                writers[i].close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("end execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }




    public  void splitNum(String[] args) {
        System.err.println("start execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try {
            FileReader fileReader=new FileReader(new File("D:\\demoFile\\number.txt"));

            BufferedReader bufferedReader=new BufferedReader(fileReader);

            BufferedWriter [] writers= new BufferedWriter[10];

            RecordNum [] recordNums=new RecordNum[10];

            for(int i=0;i<10;i++){
                writers[i]=new BufferedWriter(new FileWriter(new File("D:\\demoFile\\hashshunt\\number"+i+".txt")));
                recordNums[i]=new RecordNum();
            }

            String readStr;
           while(( readStr=bufferedReader.readLine())!=null){


               Arrays.asList(readStr.split(" "))
                       .forEach(e-> {
                           try{
                               int num=Integer.valueOf(e)%10;

                               //每行.20个

                               RecordNum recordNum = recordNums[num];

                               if(recordNum.getBuffer()==null || recordNum.getBuffer().length()<=0){
                                   recordNum.setBuffer(new StringBuffer(e));
                               }else{
                                   recordNum.getBuffer().append(" "+e);
                               }
                               recordNum.setCount(recordNum.getCount()+1);

                               if(recordNum.getCount()>=RecordNum.MAX_MODI_COUNT){
                                   writers[num].write(recordNum.getBuffer().toString()+"\n");
                                   recordNum.getBuffer().delete(0,recordNum.getBuffer().length());
                                   recordNum.setCount(0);
                               }

                           }catch (Exception ex){

                           }
                       });
           }

            bufferedReader.close();

           for(int i=0;i<10;i++){
               writers[i].write(recordNums[i].getBuffer().toString()+"\n");
               writers[i].close();
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("end execute time:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }









}
