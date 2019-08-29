package com.chuhui.blazers.socket.zlnetty.thirdexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * MyServer
 *
 * @author: cyzi
 * @Date: 2019/8/29 0029
 * @Description: 一个聊天程序
 */
public class MyChatServer {

    /**
     * 服务器首先启动，启动后，有n个客户端与之建立连接。
     * 当第一个客户端与服务器发生连接的时候，什么都没有发生，连接建立好以后，就完事了。
     * 如果第二个客户端与服务器端建立连接的时候，服务端会通知当前当前已经建立好连接的客户端：xxx已经上线
     * 当第二个客户端掉线以后，服务器端通知其他已经建立连接的客户端：xxx已经下线
     *
     * 相当于建立了一个群，无论谁说话，其他人都能看得见
     */

    public static void main(String[] args) throws InterruptedException {

        //配置服务端Nio线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());

            // 绑定端口,同步等待成功
            ChannelFuture f = b.bind(8976).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出,释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
