package com.chuhui.blazers.socket.nettyofficialexample.http.websocket;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * WebSocketCustomeOutHandler
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/9/4
 * @Description:TODO
 */
public class WebSocketCustomeOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.out.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " WebSocketCustomeOutHandler invoke write");

        if(msg instanceof TextWebSocketFrame){

            TextWebSocketFrame newFrame = (TextWebSocketFrame) msg;
            msg= newFrame.replace(Unpooled.copiedBuffer((returnCurrentTimeFormated(commonlyUserDateTimeFormat)+" this is current time").getBytes()));
        }

        ctx.write(msg,promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
        System.out.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " WebSocketCustomeOutHandler invoke flush");
    }
}
