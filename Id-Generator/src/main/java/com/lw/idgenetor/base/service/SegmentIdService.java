package com.lw.idgenetor.base.service;

import com.lw.idgenetor.base.entity.SegmentId;

public interface SegmentIdService {

    /**
     * 根据bizType获取下一个SegmentId对象
     * @param bizType
     * @return
     */
    SegmentId getNextSegmentId(String bizType);

}
