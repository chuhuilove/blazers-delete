package com.chuhui.blazers.socket.zlnetty.fifthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * TextWebSocketFrameHandler
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:TODO
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.err.println("收到消息:" + msg.text());

        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务时间:" + LocalDateTime.now()));
    }




}


