package com.chuhui.blazers.socket.nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * MSBServer
 *
 * @author: 纯阳子
 * @Date: 2019/7/15 0015
 * @Description:TODO
 */
public class MSBServer {


    public static void main(String[] args) throws IOException {


        // 打开一个通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //
        serverChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));





    }



}
