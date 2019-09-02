package com.chuhui.blazers.socket.nio.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * SelectClass
 *
 * @author: 纯阳子
 * @Date: 2019/7/15 0015
 * @Description:TODO
 */
public class SelectClass {

    private static Map<String, SocketChannel> clientMap = new HashMap<>(16);


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(41125));


        while (true) {

            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {


                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                final SocketChannel client;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                        String key = "[" + UUID.randomUUID().toString() + "]";
                        clientMap.put(key, client);

                        keys.remove(selectionKey);

                    } else if (selectionKey.isReadable()) {

                        client = (SocketChannel) selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int readBytes = client.read(byteBuffer);

                        if (readBytes > 0) {
                            byteBuffer.flip();
                            Charset charset = Charset.forName("UTF-8");

                            String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                            System.err.println(client + ":" + receiveMessage);

                            String key = null;

                            for (Map.Entry<String, SocketChannel> channel : clientMap.entrySet()) {
                                if (channel.getValue() == client) {
                                    key = channel.getKey();
                                    break;
                                }
                            }
                            for (Map.Entry<String, SocketChannel> channelEntry : clientMap.entrySet()) {
                                //TODO  某个客户端关闭后,服务端还是会向其发送数据,然后服务端会报错,怎么解决
                                //
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((key + ":" + receiveMessage).getBytes());
                                writeBuffer.flip();
                                channelEntry.getValue().write(writeBuffer);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


    }


}