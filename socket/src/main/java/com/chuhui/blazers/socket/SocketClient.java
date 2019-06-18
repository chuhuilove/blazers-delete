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

        for (int i = 0; i < 100; i++) {
        Socket socket = new Socket();

        socket.connect(new InetSocketAddress("127.0.0.1", 9007));
//       socket.connect(new InetSocketAddress("118.24.141.172", 9007));

        OutputStream os = socket.getOutputStream();




            byte[] body = (MESSAGE + " " + i).getBytes();
            os.write(body);

            System.err.println("write " + MESSAGE + " " + i);

            InputStream inputStream = socket.getInputStream();
            byte[] respBody = new byte[1024];
            inputStream.read(respBody);

            System.err.println("client rev info:" + new String(respBody));

        }
    }


}
