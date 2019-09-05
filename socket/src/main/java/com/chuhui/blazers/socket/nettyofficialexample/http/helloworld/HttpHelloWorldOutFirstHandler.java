package com.chuhui.blazers.socket.nettyofficialexample.http.helloworld;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * HttpHelloWorldOutFirstHandler
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:TODO
 */
public class HttpHelloWorldOutFirstHandler extends ChannelOutboundHandlerAdapter {


    public HttpHelloWorldOutFirstHandler() {

        System.out.println("  HttpHelloWorldOutFirstHandler has initialized ");
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.err.println(" HttpHelloWorldOutFirstHandler invoke write");

        if (msg instanceof FullHttpResponse) {


            FullHttpResponse response = (FullHttpResponse) msg;
            String mesageStr = "filter message,will change body";
            msg = response.replace(Unpooled.copiedBuffer(mesageStr.getBytes()));
        }
        ctx.write(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
