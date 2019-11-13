package com.lw.idgenetor.base.factory;

import com.lw.idgenetor.base.generator.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractIdGeneratorFactory implements IdGeneratorFactory {

    private static ConcurrentHashMap<String, IdGenerator> generators = new ConcurrentHashMap<>();

    @Override
    public IdGenerator getIdGenerator(String bizType) {
        if (generators.containsKey(bizType)) {
            return generators.get(bizType);
        }
        synchronized (this) {
            if (generators.containsKey(bizType)) {
                return generators.get(bizType);
            }
            IdGenerator idGenerator = createIdGenerator(bizType);
            generators.put(bizType, idGenerator);
            return idGenerator;
        }
    }

    protected abstract IdGenerator createIdGenerator(String bizType);

}
