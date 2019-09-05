package com.chuhui.blazers.socket.nettyofficialexample.http.upload;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

/**
 * HttpUploadServerInitialize
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:TODO
 */
public class HttpUploadServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpContentCompressor());
        pipeline.addLast(new  HttpUploadServerHandler());




    }
}
