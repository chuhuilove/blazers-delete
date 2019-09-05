package com.chuhui.blazers.socket.nettyofficialexample.http.upload;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * HttpUploadServer
 * 基于Netty的Http 服务,代码源自 Netty example
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:TODO
 */
public class HttpUploadServer {

    public static final int PORT = 10025;

    public static void main(String[] args) {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpUploadServerInitializer());

            Channel channel = b.bind(PORT).sync().channel();
            System.err.println("Open your web browser and navigate to http://127.0.0.1:" + PORT + "/");
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }


    }


}
