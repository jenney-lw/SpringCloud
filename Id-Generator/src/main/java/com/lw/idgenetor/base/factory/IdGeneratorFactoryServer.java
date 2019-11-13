package com.lw.idgenetor.base.factory;

import com.lw.idgenetor.base.generator.IdGenerator;
import com.lw.idgenetor.base.generator.impl.CachedIdGenerator;
import com.lw.idgenetor.base.service.SegmentIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGeneratorFactoryServer extends AbstractIdGeneratorFactory {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorFactoryServer.class);

    @Autowired
    private SegmentIdService tinyIdService;

    @Override
    protected IdGenerator createIdGenerator(String bizType) {
        logger.info("createIdGenerator :{}", bizType);
        return new CachedIdGenerator(bizType, tinyIdService);
    }

}
