package com.chuhui.blazers.socket.nettyofficialexample.http.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import static io.netty.handler.codec.http.HttpMethod.GET;

/**
 * WebSocketIndexPageHandler
 *
 * @author: cyzi
 * @Date: 2019/9/3 0003
 * @Description:TODO
 */
public class WebSocketIndexPageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String websocketPath;

    public WebSocketIndexPageHandler(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        if(!msg.decoderResult().isSuccess()){
            // 发送http响应

            return;
        }


        if(GET.equals(msg.method())){

        }



    }
}
