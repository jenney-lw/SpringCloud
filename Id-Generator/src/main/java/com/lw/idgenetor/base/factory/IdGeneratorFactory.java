package com.lw.idgenetor.base.factory;

import com.lw.idgenetor.base.generator.IdGenerator;

public interface IdGeneratorFactory {

    /**
     * 根据bizType创建id生成器
     * @param bizType
     * @return
     */
    IdGenerator getIdGenerator(String bizType);

}
