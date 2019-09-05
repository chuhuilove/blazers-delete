package com.chuhui.blazers.socket.nettyofficialexample.http.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * HttpServer
 *
 * 基于Netty的http服务示例,代码改编自Netty example
 *
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 *
 */
public class HttpHelloWorldServer {

    public static final int HTTP_PORT=8896;

    public static void main(String[] args) {

        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();

            b.group(boosGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpHelloWorldServerInitializer());

            Channel channel = b.bind(HTTP_PORT).sync().channel();
            System.err.println("Open your web browser and navigate to http://127.0.0.1:"+HTTP_PORT+"/");
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
