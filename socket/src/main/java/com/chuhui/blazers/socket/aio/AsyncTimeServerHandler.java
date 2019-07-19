package com.chuhui.blazers.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.Selector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class AsyncTimeServerHandler implements Runnable {

    private int port;
    private CountDownLatch latch;
    private AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncTimeServerHandler(int port) {

        this.port = port;
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

            System.err.println("the time server has started,port:" + port);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void run() {

        latch = new CountDownLatch(1);
        doAccept();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doAccept() {
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
    }

    private class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
        @Override
        public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {

        }

        @Override
        public void failed(Throwable exc, AsyncTimeServerHandler attachment) {

        }
    }
}
