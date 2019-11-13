package com.lw.idgenetor.controller;

import com.lw.idgenetor.base.factory.IdGeneratorFactoryServer;
import com.lw.idgenetor.base.generator.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/id/")
public class IdContronller {

    private static final Logger logger = LoggerFactory.getLogger(IdContronller.class);

    @Autowired
    private IdGeneratorFactoryServer idGeneratorFactoryServer;

    @Value("${batch.size.max}")
    private Integer batchSizeMax;

    @RequestMapping("nextIdSimple")
    public String nextIdSimple(String bizType, Integer batchSize, String token) {
        Integer newBatchSize = checkBatchSize(batchSize);
        String response = "";

        IdGenerator idGenerator = idGeneratorFactoryServer.getIdGenerator(bizType);
        if (newBatchSize == 1) {
            Long id = idGenerator.nextId();
            response = id + "";
        } else {
            List<Long> idList = idGenerator.nextId(batchSize);
            StringBuilder sb = new StringBuilder();
            for (Long id : idList) {
                sb.append(id).append(",");
            }
            response = sb.deleteCharAt(sb.length() - 1).toString();
        }

        return response;
    }


    private Integer checkBatchSize(Integer batchSize) {
        if (batchSize == null) {
            batchSize = 1;
        }
        if (batchSize > batchSizeMax) {
            batchSize = batchSizeMax;
        }
        return batchSize;
    }

}
