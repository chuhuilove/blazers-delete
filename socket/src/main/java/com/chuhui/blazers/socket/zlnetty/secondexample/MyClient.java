package com.chuhui.blazers.socket.zlnetty.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * MyClient
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:TODO
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());

            ChannelFuture future = bootstrap.connect("localhost", 8986).sync();

            future.channel().closeFuture().sync();
        } finally {
            eventGroup.shutdownGracefully();
        }
    }
}
