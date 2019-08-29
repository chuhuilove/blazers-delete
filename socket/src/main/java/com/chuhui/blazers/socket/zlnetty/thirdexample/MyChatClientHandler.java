package com.chuhui.blazers.socket.zlnetty.thirdexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MyChatClientHandler
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:TODO
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.err.println(msg);
    }
}
