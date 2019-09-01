package com.chuhui.blazers.socket.nio.buffer;

import java.nio.ByteBuffer;

/**
 * BufferSplice
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 *  Buffer的分片, 只读
 * @author: 纯阳子
 * @Date: 2019/9/1
 * @Description:TODO
 */
public class BufferSpliceAndReadOnly {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(10);


        /**
         * 调用slice方法时,意味着从原始数组的position位置到limit位置,复制出一份数据,存储到新的Buffer里面
         *
         * 新的buffer拥有独立的position,limit等属性,但是新的buffer所拥有的数据,还是原始buffer里面的数据
         *
         * ByteBuffer的实现者,无论是HeapByteBuffer还是DirectByteBuffer,其内部维护的都是一个byte数组
         *
         * 而slice的时候,并没有从原始数组中复制出一份独立的数组,而是两个buffer公用一个数组.
         * 这样的话,两个buffer的修改,都是相互可见的.
         *
         */

        ByteBuffer sliceBuffer = buffer.slice();


        // 返回一个只读buffer,提供给调用端
        // 可以将任何一个读写buffer转换为一个只读buffer
        // 但是不能将一个只读Buffer转换为一个读写buffer
        // 主要原因就是:不允许调用者修改数据
        ByteBuffer buffer1 = buffer.asReadOnlyBuffer();




    }






}
