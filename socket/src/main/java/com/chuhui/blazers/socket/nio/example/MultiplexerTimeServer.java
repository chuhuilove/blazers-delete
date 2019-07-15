package com.chuhui.blazers.socket.nio.example;

import com.chuhui.blazers.socket.SocketServer;

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
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * MultiplexerTimeServer
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description:TODO
 */
public class MultiplexerTimeServer implements Runnable {

    private int port;

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        this.port = port;

        try {

            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();

            serverChannel.configureBlocking(false);

            serverChannel.socket().bind(new InetSocketAddress(port), 1024);

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

    private void doWrite(SocketChannel sc) throws IOException {

        String response = LocalDateTime.now().format(DateTimeFormatter.ofPattern(SocketServer.FORMATTER_STR));

        byte[] bytes = response.getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
        System.err.println("已经写出去...." + response);
    }
}
