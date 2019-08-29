package com.chuhui.blazers.socket.zlnetty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * MyClientHandler
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:TODO
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.err.println(ctx.channel().remoteAddress());

        System.err.println("client output:"+msg);

        ctx.writeAndFlush("form client:"+ LocalDateTime.now());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
        ctx.writeAndFlush("来自于客户端的问候！！！");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
