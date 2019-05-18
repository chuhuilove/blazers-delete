package com.chuhui.blazers.socket.netty.stickypack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimerClientHandler extends ChannelInboundHandlerAdapter {


    private byte[] req;
    private int counter;

    public TimerClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty(TimerServer.LINE_SEPARATOR)).getBytes();


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        System.err.println(" Now is :" + new String(req, "UTF-8") + "; " + (++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
