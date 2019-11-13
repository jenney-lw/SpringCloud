package com.lw.idgenetor.base.service.impl;

import com.lw.idgenetor.base.entity.SegmentId;
import com.lw.idgenetor.base.exception.IdSysException;
import com.lw.idgenetor.base.service.SegmentIdService;
import com.lw.idgenetor.common.Constants;
import com.lw.idgenetor.dao.TinyIdInfoDAO;
import com.lw.idgenetor.dao.entity.TinyIdInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class DbSegmentIdServiceImpl implements SegmentIdService {

    private static final Logger logger = LoggerFactory.getLogger(DbSegmentIdServiceImpl.class);

    @Autowired
    private TinyIdInfoDAO tinyIdInfoDAO;

    @Override
    public SegmentId getNextSegmentId(String bizType) {
        for (int i = 0; i < Constants.RETRY; i++) {
            TinyIdInfo tinyIdInfo = tinyIdInfoDAO.queryByBizType(bizType);
            if (tinyIdInfo == null) {
                throw new IdSysException("can not find biztype:" + bizType);
            }
            Long newMaxId = tinyIdInfo.getMaxId() + tinyIdInfo.getStep();
            Long oldMaxId = tinyIdInfo.getMaxId();
            int row = tinyIdInfoDAO.updateMaxId(tinyIdInfo.getId(), newMaxId, oldMaxId, tinyIdInfo.getVersion());
            if (row == 1) {
                tinyIdInfo.setMaxId(newMaxId);
                SegmentId segmentId = convert(tinyIdInfo);
                logger.info("getNextSegmentId success tinyIdInfo:{} current:{}", tinyIdInfo, segmentId);
                return segmentId;
            } else {
                logger.info("getNextSegmentId conflict tinyIdInfo:{}", tinyIdInfo);
            }
        }
        return null;
    }

    public SegmentId convert(TinyIdInfo tinyIdInfo) {
        SegmentId segmentId = new SegmentId();
        segmentId.setMaxId(tinyIdInfo.getMaxId());
        segmentId.setCurrentId(new AtomicLong(tinyIdInfo.getMaxId() - tinyIdInfo.getStep()));
        segmentId.setRemainder(tinyIdInfo.getRemainder() == null ? 0 : tinyIdInfo.getRemainder());
        segmentId.setDelta(tinyIdInfo.getDelta() == null ? 1 : tinyIdInfo.getDelta());
        // 默认20%加载
        segmentId.setLoadingId(segmentId.getCurrentId().get() + tinyIdInfo.getStep() * Constants.LOADING_PERCENT / 100);
        return segmentId;
    }

}
