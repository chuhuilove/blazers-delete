package com.chuhui.blazers.socket.nettyofficialexample.http.websocket;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * WebSocketCustomeOutHandlerEn
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/9/4
 * @Description:TODO
 */
public class WebSocketCustomeOutHandlerEn extends MessageToMessageEncoder<WebSocketFrame> {


    public WebSocketCustomeOutHandlerEn(){
        System.err.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " WebSocketCustomeOutHandlerEn initialized");
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        System.out.println(returnCurrentTimeFormated(commonlyUserDateTimeFormat) + " WebSocketCustomeOutHandlerEn invoke encode");

        if(msg instanceof TextWebSocketFrame){
            TextWebSocketFrame newMsg=(TextWebSocketFrame)msg;
            System.out.println("转换之前的message:"+newMsg.text());

            msg=newMsg.replace(Unpooled.copiedBuffer(("妈的.这是编码:"+System.currentTimeMillis()).getBytes()));
        }
        ctx.write(msg);
    }

}
