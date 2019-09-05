package com.chuhui.blazers.socket.nettyofficialexample.http.helloworld;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.GlobalEventExecutor;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaderValues.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * HttpHelloWorldServerHandler
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:TODO
 */
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static ChannelGroup clientGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static final String FAVICONICO_NAME="/favicon.ico";

    public HttpHelloWorldServerHandler() {
        System.out.println("HttpHelloWorldServerHandler initialized");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;

            boolean keepAlive = HttpUtil.isKeepAlive(req);

            String messageStr="Hello World:welcome to http server";

            // 判断一下,做一个路由转发
            // 不同的请求过来以后,回应不同的数据
            if(FAVICONICO_NAME.equals(req.uri())){
                messageStr="Hello World: this is favorite.ico";
            }

            // 准备http响应体对象
            FullHttpResponse res = new DefaultFullHttpResponse(req.protocolVersion(), OK, Unpooled.copiedBuffer(messageStr.getBytes()));

            // 设置响应头
            res.headers().set(CONTENT_TYPE, TEXT_PLAIN)
                    .setInt(CONTENT_LENGTH, res.content().readableBytes());

            System.out.println("keepAlive:" + keepAlive + ", res.uri:" + req.uri() + ",handler:" + this.toString());


            if (keepAlive) {
                if (!req.protocolVersion().isKeepAliveDefault()) {
                    res.headers().set(CONNECTION, KEEP_ALIVE);
                }
            } else {
                // 告诉客户端,服务端将要关闭
                res.headers().set(CONNECTION, CLOSE);
            }

            // 向客户端写数据
            ChannelFuture future = ctx.writeAndFlush(res);

            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }


            /**
             * 2019年9月5日14:02:55
             * req.uri()的结果可以分配路由..
             * netty这一块,貌似已经有一些头绪了..
             *
             * 以后有时间,自己可以做一个Netty服务器
             *
             */

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " invoke channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " invoke handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " invoke handlerRemoved");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
