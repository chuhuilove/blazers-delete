package com.chuhui.blazers.socket.nio.example;

import com.chuhui.blazers.socket.stickypack.socket.SocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * MultiplexerTimeServer
 * nio编程和epoll对比
 * 参考其中的注释
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description: 原始nio编程
 */
public class MultiplexerTimeServer implements Runnable {

    private int port;

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        this.port = port;

        try {

            /*
            *  Selector.open();
            * 可以理解为调用了epoll_create();
            * 返回值是epoll的句柄
            *
            * */
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();

            // 新创建的通道,设置为非阻塞模式
            serverChannel.configureBlocking(false);

            // 绑定地址端口
            serverChannel.socket().bind(new InetSocketAddress(port));
            // 将serverChannel 注册到selector中,并且设置其感兴趣的事件为接收请求
            /*
            *  serverChannel.register();
            * 可以理解为调用了epoll_ctl;
            *
            * 较为完整的epoll_ctl 为
            *
            * struct epoll_event tep;
            * tep.events=EPOLLIN; 监听读事件,
            * tep.data.fd=listenfd;
            *
            * res=epoll_ctl(efd,EPOLL_CTL_ADD,listenfd,&tep);
            * etd: 这里可以类比为selector指针
            * EPOLL_CTL_ADD: 描述符的操作,这里是添加
            * listenfd: 可以类比为serverChannel
            *
            *
            *
            */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);


            System.err.println("the time server is start in port:" + port);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {


        while (!stop) {
            CommonUtil.commonMethod(selector, (key)->handleInput(key));
        }

        if (selector != null) {

            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private void doWrite(SocketChannel sc) throws IOException {

        String response =  returnCurrentTimeFormated(commonlyUserDateTimeFormat);

        byte[] bytes = response.getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
        System.err.println("已经写出去...." + response);
    }

    private void handleInput(SelectionKey key) {
        try {
            if (key.isValid()) {

                if (key.isAcceptable()) {

                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = null;

                    sc = ssc.accept();


                    System.err.println("接收到连接请求....");

                    sc.configureBlocking(false);

                    sc.register(selector, SelectionKey.OP_READ);
                }


                if (key.isReadable()) {

                    System.err.println("接收到数据了嘛......");

                    SocketChannel sc = (SocketChannel) key.channel();

                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);

                    if (readBytes > 0) {

                        readBuffer.flip();

                        byte[] bytes = new byte[readBuffer.remaining()];

                        readBuffer.get(bytes);

                        System.err.println("接收到的数据:" + new String(bytes, Charset.forName("UTF-8")));

                        doWrite(sc);
                    } else if (readBytes < 0) {

                        // 客户端已经关闭
                        key.cancel();
                        sc.close();
                    } else {
                        // 读到0个字节,什么都不做
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
