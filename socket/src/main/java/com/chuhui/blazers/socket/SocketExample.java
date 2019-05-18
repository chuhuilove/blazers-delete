package com.chuhui.blazers.socket;

import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * java  socket 示例
 *
 * 模拟tcp粘包与拆包
 *
 * @author: 纯阳子
 * @Date: 2019/5/18
 * @Description:TODO
 */
public class SocketExample {


    public static void main(String[] args) {


        Socket socket=new Socket();
        SocketChannel channel = socket.getChannel();
        if(!channel.isOpen()){
            System.err.println("通道没有打开");
        }

    }


}
