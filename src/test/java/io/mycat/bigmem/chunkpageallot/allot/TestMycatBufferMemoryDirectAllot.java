package io.mycat.bigmem.chunkpageallot.allot;

import io.mycat.bigmem.chunkpageallot.console.ChunkMemoryAllotEnum;

/**
 * 测试内存的可移动的分配操作
 * or liujun
 *  2016年12月30日
 * @version 0.0.1
 */
public class TestMycatBufferMemoryDirectAllot extends TestMycatBufferAllotBase {

    @Override
    protected int getAllocFlag() {
        return ChunkMemoryAllotEnum.MEMORY_DIRECT.getLevel();
    }


}
