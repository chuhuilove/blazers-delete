package com.chuhui.blazers.socket.nio.buffer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;

/**
 * FileChannel
 * 文件通道示例
 *
 * @author: cyzi
 * @Date: 2019/8/30 0030
 * @Description:TODO
 */
public class FileChannelExample {


    public static void main(String[] args) throws IOException {


        FileChannel inputChannel = new FileInputStream("input.txt").getChannel();
        FileChannel outputChannel = new FileOutputStream("output.txt").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(2048);

        while (true) {
//            buffer.clear();
            // 如果不调用clear,则position和limit相等,无法进行读数据,则会返回0,除非channel关闭后才能返回-1,
            int read = inputChannel.read(buffer);
            System.err.println("read:" + read);
            if (read <= -1) {
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }
        inputChannel.close();
        outputChannel.close();
    }


}
