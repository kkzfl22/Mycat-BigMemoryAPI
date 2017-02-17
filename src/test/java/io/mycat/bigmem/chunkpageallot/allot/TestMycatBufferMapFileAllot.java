package io.mycat.bigmem.chunkpageallot.allot;

import io.mycat.bigmem.chunkpageallot.console.ChunkMemoryAllotEnum;

/**
 * 用来测试内存映射文件的相关的测试
 * @author kk
 * @version 0.0.1
 */
public class TestMycatBufferMapFileAllot extends TestMycatBufferAllotBase {

    @Override
    protected int getAllocFlag() {
        return ChunkMemoryAllotEnum.MEMORY_MAPFILE.getLevel();
    }

}
