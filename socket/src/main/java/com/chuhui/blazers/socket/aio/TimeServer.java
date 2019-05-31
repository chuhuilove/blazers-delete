package com.chuhui.blazers.socket.aio;

public class TimeServer {

    public static void main(String[] args) {
        new Thread(new AsyncTimeServerHandler(8003)).start();
    }

}
