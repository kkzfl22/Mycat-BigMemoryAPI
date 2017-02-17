package io.mycat.bigmem.chunkpageallot.buffer.directmemory;

import java.io.IOException;

import io.mycat.bigmem.chunkpageallot.MemoryAllocatorInf;
import io.mycat.bigmem.chunkpageallot.MycatMemoryAllocatorFactory;
import io.mycat.bigmem.chunkpageallot.buffer.MycatBufferBase;

public class TestDirectBufferPool {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 构建内存池对象
        MemoryAllocatorInf memoryAllot = MycatMemoryAllocatorFactory.createMemoryAlloctor();

        // 进行内存的申请
        MycatBufferBase buffer = memoryAllot.allocMem(1, 1024);

        buffer.beginOp();

        // 进行内存数据卦
        fillValue(buffer);

        printValue(buffer);

        // 进行内存的回收
        // 测试全部回收
        // 回收内存之前，需要设置limit的大小，以确定回收的内存大小
        buffer.limit(256);
        memoryAllot.recyleMem(buffer);

        // 测试内存归还后，是否可继续申请
        MycatBufferBase buffer2 = memoryAllot.allocMem(1, 1792);

        System.out.println("内存空间大小:" + buffer2.limit());

        buffer.commitOp();
    }

    /**
     * 进行内存的填充
    * 方法描述
    * @param buffer
     * @throws IOException 
    *  2016年12月23日
    */
    public static void fillValue(MycatBufferBase buffer) throws IOException {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.putByte((byte) i);
        }
    }

    public static void printValue(MycatBufferBase buffer) {
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print("curr value:" + buffer.get() + "\t");
            if (i % 4 == 0) {
                System.out.println();
            }
        }
        
        System.out.println();
        System.out.println();
    }

}
