
`ByteBuffer` 两种创建方式`ByteBuffer.allocate`和`ByteBuffer.allocateDirect`





`ByteBuffer`对象是固定数量的数据的容器,和`List`下的`ArrayList`类似,内核都是一个数组,与`ArrayList`不同的是,`ByteBuffer`内部是一个`final byte[] hb`.



操纵`ByteBuffer`,本质上就是在操纵数组,所有的缓冲区都具有四个属性来提供关于其所包含的数据元素的信息:






这里需要明确一个概念:直接缓冲区和非直接缓冲区

直接缓冲区通过`ByteBuffer.allocateDirect`创建,其操纵的为本机物理内存,由`DirectByteBuffer`创建.

非直接缓冲区由`ByteBuffer.allocate`创建,其操纵的虚拟机的内存,由`HeapByteBuffer`创建.






