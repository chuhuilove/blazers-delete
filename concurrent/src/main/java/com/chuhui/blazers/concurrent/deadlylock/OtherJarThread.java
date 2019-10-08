package com.chuhui.blazers.concurrent.deadlylock;

import static com.chuhui.blazers.commcustome.constant.Constaints.*;

/**
 * OtherJarThread
 * <p>
 * 
 *
 * @author: 纯阳子
 * @date: 2019/10/7
 */
public class OtherJarThread extends Thread {

    @Override
    public void run() {
        synchronized (CUSTOME_STR) {
            int i = 0;
            while (i++ < Integer.MAX_VALUE) {
                System.err.println( "OtherJarThread---->"+returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " 平台提供的任务");

            }

        }
    }

}
