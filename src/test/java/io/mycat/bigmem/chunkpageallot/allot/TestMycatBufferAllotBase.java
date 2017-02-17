package io.mycat.bigmem.chunkpageallot.allot;

import org.junit.Before;
import org.junit.Test;

import io.mycat.bigmem.chunkpageallot.MemoryAllocatorInf;
import io.mycat.bigmem.chunkpageallot.MycatMemoryAllocatorFactory;
import io.mycat.bigmem.chunkpageallot.buffer.MycatBufferBase;
import io.mycat.bigmem.chunkpageallot.console.ChunkMemoryAllotEnum;

/**
 * 测试内存的优先分配操作
 * or liujun
 *  2016年12月30日
 * @version 0.0.1
 */
public abstract class TestMycatBufferAllotBase {

    /**
     * 获取内存分配的优化级
     * @return
     */
    protected abstract int getAllocFlag();

    /**
     * 内存分配的优化级参数
     */
    private int allocFlag = 0;

    /**
     * 内存分配的优化级的参数
     */
    private MemoryAllocatorInf memoryAllot;

    @Before
    public void beforeinit() {

        memoryAllot = MycatMemoryAllocatorFactory.createMemoryAlloctor();

        // 获取内存分配的优化级的参数
        allocFlag = this.getAllocFlag();

    }

    /**
     * 测试内存的分配操作
     */
    @Test
    public void memoryAlloc() {

        MycatBufferBase buffer = memoryAllot.allocMem(allocFlag, 512);

        System.out.println(buffer.limit());

        MycatBufferBase buffer2 = memoryAllot.allocMem(allocFlag, 512);

        System.out.println(buffer2.limit());

        System.out.println("test memory alloc over");
    }

    /**
     * 测试内存的回收
     */
    @Test
    public void memoryRecycle() {
        MycatBufferBase buffer = memoryAllot.allocMem(allocFlag, 512);

        try {
            buffer.beginOp();
            // 加收内存需要重新标识容量limit
            buffer.limit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            buffer.commitOp();
        }
        
        
        // 进行内存的回收
        memoryAllot.recyleMem(buffer);

        // 回收完毕之后的内存
        System.out.println("recycle limit:" + buffer.capacity());
    }

}
