package com.chuhui.blazers.socket.nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * TimeClientHandle
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description:TODO
 */
public class TimeClientHandle implements Runnable {

    private String hostName;
    private int port;

    private Selector selector;
    private SocketChannel socketChannel;

    private volatile boolean stop;


    public TimeClientHandle(String hostName, int port) {

        this.hostName = hostName;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!stop) {
            CommonUtil.commonMethod(selector, (key) -> handleInput(key));
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

                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (sc.finishConnect()) {
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite();
                    } else {
                        System.exit(1);
                    }

                    if (key.isReadable()) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        // 可以进行判断,是否已经读取完毕
                        if (readBytes > 0) {
                            readBuffer.flip();

                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            System.err.println("now is:" + new String(bytes, Charset.forName("UTF-8")));

                            stop = true;
                        } else if (readBytes < 0) {
                            key.cancel();
                            sc.close();
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doConnect() throws IOException {

        boolean connect = socketChannel.connect(new InetSocketAddress(hostName, port));

        if (connect) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite();
        } else {
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

    }

    private void doWrite() throws IOException {

        byte[] bytes = "Query Time order".getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        // 如果不进行flip ,在write的时候,会提取 position 到 limit之间的数据
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        if (!writeBuffer.hasRemaining()) {
            System.err.println("Send order 2 server succeed.");
        }


    }
}
