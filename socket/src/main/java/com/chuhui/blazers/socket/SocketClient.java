package com.chuhui.blazers.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * java  socket 示例
 * <p>
 * 模拟tcp粘包与拆包
 *
 * @author: 纯阳子
 * @Date: 2019/5/18
 * @Description:TODO
 */
public class SocketClient {


    public static void main(String[] args) throws IOException {


        Socket socket = new Socket();

        socket.connect(new InetSocketAddress("127.0.0.1", 8081));


        for (int i = 0; i < 100; i++) {


            OutputStream os = socket.getOutputStream();
            byte[] body = ("this is a message"+i).getBytes();
            os.write(body);
//
            System.err.println("client send :"+i);
            os.flush();
        }
//        SocketChannel channel = socket.getChannel();
//        if(channel.isOpen()){
//            System.err.println("channel is not opened");
//        }
//
//        byte[] body = "this is a message".getBytes();
//        ByteBuffer buffer=ByteBuffer.allocateDirect(body.length);
//        buffer.put(body);
//        buffer.flip();
//        for(int i=0;i<100;i++){
//            channel.write(buffer);
//        }


    }


}
