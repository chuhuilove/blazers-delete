package com.chuhui.blazers.commcustome.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Constaints
 *
 * @author: 纯阳子
 * @Date: 2019/7/26 0026
 * @Description: 定义blazers的常量
 */
public class Constaints {

    /**
     * 常用的日期事件格式,精确到毫秒 yyyyMMdd hh:mm:ss:SSS
     */
    public static final DateTimeFormatter commonlyUserDateTimeFormatNoHorizontalLine = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss:SSS");

    /**
     * 常用的日期事件格式,精确到毫秒 yyyy-MM-dd hh:mm:ss:SSS
     */
    public static final DateTimeFormatter commonlyUserDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");


    /**
     * 返回当前时间格式化后的字符串
     *
     * @param format 格式化的形式 如{@link Constaints#commonlyUserDateTimeFormat}等
     * @return
     */
    public static String returnCurrentTimeFormated(DateTimeFormatter format) {
        return LocalDateTime.now().format(format);
    }

}
