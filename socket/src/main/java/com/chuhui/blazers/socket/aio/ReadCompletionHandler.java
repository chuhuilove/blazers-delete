package com.chuhui.blazers.socket.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ReadCompletionHandler
 *
 * @author: 纯阳子
 * @Date: 2019/7/25 0025
 * @Description:TODO
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousServerSocketChannel channel;

    public ReadCompletionHandler(AsynchronousServerSocketChannel channel) {

        if (this.channel == null) {
            this.channel = channel;
        }
    }


    @Override
    public void completed(Integer result, ByteBuffer attachment) {

        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);

        try {
            String req = new String(body, "UTF-8");
            System.err.println("The time server receive order:" + req);

            String currentTime = String.valueOf(System.currentTimeMillis());

            doWrite(currentTime);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String currentTime) {


    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
