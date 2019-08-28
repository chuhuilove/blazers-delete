package com.chuhui.blazers.socket.zlnetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * TestInitlter
 *
 * @author: cyzi
 * @Date: 2019/8/28 0028
 * @Description:TODO
 */
public class TestInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("httpServerHandler",new TestHttpServerHandler());
    }
}
