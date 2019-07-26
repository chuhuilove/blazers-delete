package com.chuhui.blazers.socket.nio.example;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormatNoHorizontalLine;

/**
 * PoolServer
 *
 * @author: 纯阳子
 * @Date: 2019/7/26 0026
 * @Description: nio和线程池结合
 */

//FIXME 2019年7月26日15:19:51
// 未进行测试程序是否可运行
public class PoolServer {


    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 100L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(20), new CustomerThreadFactory("nio-server-pooled-"));


    private int port;

    private Selector selector;

    public PoolServer(int port) {
        this.port = port;
    }


    public static void main(String[] args) {

        PoolServer poolServer = new PoolServer(10901);
        poolServer.initServer();
        poolServer.listen();
    }


    public void listen() {
        try {
            while (true) {

                /**
                 * 获取已经就绪的key.
                 * 该方法是一个阻塞方法,等待操作系统将key填充到应用程序中
                 *
                 * 从Linux内核的角度来讲,存储所有socket的数据结构是一个棵红黑树
                 *
                 * select()阻塞函数,等待这棵红黑树上有节点发生事件(如:读,写等)
                 * 直到最起码一个节点出现响应事件,
                 * 然后jvm会将这些有事件响应的节点的引用存储到一个Set中
                 *
                 * 最后上层应用遍历这个set,处理set集合中每一个元素的事件
                 *
                 * 在上层应用遍历Set集合的时候,会调用remove方法,从集合中将该节点删除.
                 * 这个删除,也就是直接将节点从红黑树上摘掉.
                 *
                 */
                selector.select();

                Set<SelectionKey> keys = selector.keys();

                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    // 从红黑树上摘掉节点
                    iterator.remove();


                    if (key.isAcceptable()) {

                        ServerSocketChannel server = (ServerSocketChannel) key.channel();

                        SocketChannel channel = server.accept();

                        channel.configureBlocking(false);

                        // 重新将这个通道注册进红黑树中,这时这个节点的监听事件是OP_READ
                        channel.register(this.selector, SelectionKey.OP_READ);
                    } else {

                        key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                        executor.execute(() -> new ThreadHandlerChannel(key));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initServer() {

        try {
            //1.打开socketserverchannel
            ServerSocketChannel channel = ServerSocketChannel.open();
            //2. 绑定端口
            channel.bind(new InetSocketAddress(port));
            //3. 设置该channel非非阻塞模式
            channel.configureBlocking(false);

            //4. 打开一个Selector
            selector = Selector.open();
            //5. 将 channel 注册到 Selector中
            channel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class ThreadHandlerChannel implements Runnable {

        private SelectionKey selectKey;

        public ThreadHandlerChannel(SelectionKey selectionKey) {
            this.selectKey = selectionKey;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) selectKey.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            try {
                //读数据
                channel.read(readBuffer);

                readBuffer.flip();

                byte[] readBytes = new byte[readBuffer.limit()];
                System.err.println("读取的数据是:" + new String(readBytes));


                String respone = "this is server respone data,current time is :" + LocalDateTime.now().format(commonlyUserDateTimeFormatNoHorizontalLine);

                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                writeBuffer.put(respone.getBytes());
                writeBuffer.flip();

                // 写数据
                channel.write(readBuffer);

                selectKey.interestOps(selectKey.interestOps() | SelectionKey.OP_READ);


                // 其他的错误处理,不做了

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
