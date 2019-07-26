package com.chuhui.blazers.socket.stickypack.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

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


    static final String MESSAGE = "this is a message" + System.lineSeparator();

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("118.24.141.172", 9007));


        OutputStream os = socket.getOutputStream();

        byte[] body = (MESSAGE).getBytes();
        os.write(body);

        System.err.println("@@@ write " + MESSAGE + " @@@ ");


//        InputStream inputStream = socket.getInputStream();
//        byte[] respBody = new byte[1024];
//        inputStream.read(respBody);
//
//        System.err.println("client rev info:" + new String(respBody));
    }

}
