package com.chuhui.blazers.socket.aio;

/**
 * ReadCompletionHandler
 *
 * @author: 纯阳子
 * @Date: 2019/7/25 0025
 * @Description: AIO 编程
 */
public class TimeServer {

    public static void main(String[] args) {
        new Thread(new AsyncTimeServerHandler(8003)).start();
    }

}
