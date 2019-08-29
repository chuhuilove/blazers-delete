package com.chuhui.blazers.socket.zlnetty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * MyServerHandler
 * 最主要的,还是搞懂回调函数中的意思.
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description:
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    static private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(e -> {
            if (e != channel) {
                e.writeAndFlush("client:" + channel.remoteAddress() + " 发言了：" + msg + System.lineSeparator());
            } else {
                e.writeAndFlush("【自己】" + msg + System.lineSeparator());
            }
        });
    }

    /**
     * 服务器端已经与客户端建立好了连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channelActive = ctx.channel();

        channelGroup.writeAndFlush("【服务器】-" + channelActive.remoteAddress() + " 加入群聊" + System.lineSeparator());

        channelGroup.add(channelActive);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        /**
         * 断掉的连接，Netty会自动从channelGroup将无效的东西移除掉
         */
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + " 离开群聊" + System.lineSeparator());
        System.err.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.err.println("客户端 -" + channel.remoteAddress() + " 上线" + System.lineSeparator());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + " 下线" + System.lineSeparator());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
