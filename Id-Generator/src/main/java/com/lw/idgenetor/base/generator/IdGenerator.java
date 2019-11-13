package com.lw.idgenetor.base.generator;

import java.util.List;

public interface IdGenerator {

    Long nextId();

    List<Long> nextId(Integer batchSize);

}
