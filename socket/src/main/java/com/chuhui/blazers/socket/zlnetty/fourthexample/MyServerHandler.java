package com.chuhui.blazers.socket.zlnetty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * MyServerHandler
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:TODO
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲！！！" + System.lineSeparator();
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲！！！" + System.lineSeparator();
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲！！！" + System.lineSeparator();
                    break;
                default:
                    break;
            }
            System.err.println(ctx.channel().remoteAddress() + " 超时事件：" + eventType);
            ctx.channel().close();
        }
    }
}
