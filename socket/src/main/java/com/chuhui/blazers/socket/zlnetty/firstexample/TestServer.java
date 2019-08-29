package com.chuhui.blazers.socket.zlnetty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TestServer
 *
 * @author: cyzi
 * @Date: 2019/8/28 0028
 * @Description:
 */
public class TestServer {


    public static void main(String[] args) throws InterruptedException {
        //配置服务端Nio线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestInitializer());


            // 绑定端口,同步等待成功
            ChannelFuture f = b.bind(8996).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出,释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
