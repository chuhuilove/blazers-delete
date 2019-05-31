package com.chuhui.blazers.socket;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * java  socket 示例
 * <p>
 * 模拟tcp粘包与拆包
 * wireshark 工具,进行抓包
 *
 * @author: 纯阳子
 * @Date: 2019/5/18
 * @Description:TODO
 */
public class SocketClient {


    static final String MESSAGE = "this is a message";

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket();

        socket.connect(new InetSocketAddress("172.16.23.115", 8089));


// 一问一答的情况下,不会出现TCP粘包与拆包


        OutputStream os = socket.getOutputStream();

        for (int i = 0; i < 100; i++) {


            byte[] body = (MESSAGE + " "+i).getBytes();
            os.write(body);

            System.err.println("write "+MESSAGE + " "+i);

            InputStream inputStream = socket.getInputStream();
            byte[] respBody = new byte[1024];
            inputStream.read(respBody);


            System.err.println("client rev info:" + new String(respBody));

        }


    }


}
