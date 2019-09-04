package com.chuhui.blazers.socket.nettyofficialexample.http.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

/**
 * WebSocketServerInitializer
 *
 * @author: cyzi
 * @Date: 2019/9/3 0003
 * @Description:TODO
 */
public class WebSocketServerInitializer extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {

    public static final String WEBSOCKET_PATH="/websocket";

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("1",new HttpServerCodec());
        pipeline.addLast("2",new HttpObjectAggregator(65536));
        pipeline.addLast("3",new WebSocketServerCompressionHandler());
        pipeline.addLast("4",new WebSocketServerProtocolHandler(WEBSOCKET_PATH,null,true));
        pipeline.addLast("5",new WebSocketIndexPageHandler(WEBSOCKET_PATH));
        pipeline.addLast("6",new WebSocketFrameHandler());
        pipeline.addLast("7",new WebSocketCustomeOutHandlerEn());
        pipeline.addLast("8",new WebSocketCustomeOutHandler());



    }
}
