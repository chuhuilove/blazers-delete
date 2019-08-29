package com.chuhui.blazers.socket.zlnetty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * MyClient
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:
 */
public class MyChatClient {
    public static void main(String[] args) throws Exception {

        EventLoopGroup eventGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8976).sync().channel();

            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(inputStreamReader.readLine() + System.lineSeparator());
            }

        } finally {
            eventGroup.shutdownGracefully();
        }
    }
}
