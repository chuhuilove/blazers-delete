package com.chuhui.blazers.socket.nio.buffer;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.charset.Charset;

public class BufferExample {


    public static void main(String[] args) {

        ByteBuffer buffer = bufferGen();


        byte[] tag = new byte[6];

        buffer.get(tag, 0, 6);

        System.err.println("tag:"+new String(tag, Charset.forName("UTF-8")));

        tag=new byte[16];
        buffer.get(tag,0,16);

        System.err.println("key:"+new String(tag, Charset.forName("UTF-8")));


        System.err.println("version:"+buffer.get());
        System.err.println("length:"+buffer.getInt());
        System.err.println("commandCode:"+buffer.getShort());
//        	<QMDB>报文开始标识,固定字符串占用6个字节
//	Key :  占用16个字节的数字字符串, 加密表示,约定为”ZSMART_QMDB”,(考虑到效率,代码实现中暂没有使用)
//	Version：表明CSP协议版本1。
//	Message Length：定义为4个字节，用整形表示,指明该消息的字节长度，包括头字段。
//	Command-Code：该命令码字段为2个字节，用于表明与该消息相关联的命令。
//	Sid 服务端返回的唯一会话ID,占用4个字节,无符号整数
//	date-time：发包时间,占用6个字节,用时分秒表示
//	Sequence: 表明本次消息的sequence,由客户端产生，在连接级别上保持唯一。
//	AVPs： AVP是封装与业务消息相关的一种方法,参见后面章节。


        System.err.println("sessionId:"+(buffer.getInt()& 0xffffffffL));

        tag=new byte[6];
        buffer.get(tag,0,6);
        System.err.println("date-time:"+new String(tag,Charset.forName("UTF-8")));

        System.err.println("sequenceNo:"+buffer.getInt());
//

        FloatBuffer floatBuffer=FloatBuffer.allocate(1024);




    }


    public static ByteBuffer bufferGen() {


        byte[] bytes = new byte[]{60, 81, 77, 68, 66, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 66, 0, -56, 0, 0, 0, 120, 0, 48, 48, 48, 48, 48, 0, 0, 0, 1, 0, 4, 0, 8, 0, 0, 0, 0, 0, 2, 0, 8, 0, 0, 0, 0, 0, 3, 0, 7, 79, 75, 0};


        ByteBuffer result = ByteBuffer.allocate(1024);
        result.put(bytes, 0, bytes.length);

        result.flip();

        return result;

    }


}
