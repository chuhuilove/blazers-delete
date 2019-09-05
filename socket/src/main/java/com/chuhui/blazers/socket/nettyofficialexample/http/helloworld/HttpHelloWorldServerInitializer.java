package com.chuhui.blazers.socket.nettyofficialexample.http.helloworld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

/**
 * HttpHelloWorldServerInitializer
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:
 */
public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new HttpHelloWorldOutFirstHandler());
        pipeline.addLast(new HttpHelloWorldServerHandler());


        /**
         * 如果   pipeline.addLast(new HttpHelloWorldOutFirstHandler());
         * 放在    pipeline.addLast(new HttpHelloWorldServerHandler());
         * 的后面,则永远不会触发{@linkplain HttpHelloWorldOutFirstHandler#write(ChannelHandlerContext, Object, ChannelPromise)}
         * 方法.
         *
         * 在{@linkplain ChannelPipeline}中有详细的翻译
         *
         */



    }
}
