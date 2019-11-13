package com.lw.idgenetor.base.generator.impl;

import com.lw.idgenetor.base.entity.Result;
import com.lw.idgenetor.base.entity.ResultCode;
import com.lw.idgenetor.base.entity.SegmentId;
import com.lw.idgenetor.base.exception.IdSysException;
import com.lw.idgenetor.base.generator.IdGenerator;
import com.lw.idgenetor.base.service.SegmentIdService;

import java.util.List;

public class CachedIdGenerator implements IdGenerator {

    protected String bizType;

    protected SegmentIdService segmentIdService;

    protected volatile SegmentId current;

    protected volatile SegmentId next;

    public CachedIdGenerator(String bizType, SegmentIdService segmentIdService) {
        this.bizType = bizType;
        this.segmentIdService = segmentIdService;

        loadCurrent();
    }

    public synchronized void loadCurrent() {
        if (current == null || !current.useful()) {
            if (next == null) {
                SegmentId segmentId = querySegmentId();
                this.current = segmentId;
            } else {
                current = next;
                next = null;
            }
        }
    }

    private SegmentId querySegmentId() {
        String message = null;
        try {
            SegmentId segmentId = segmentIdService.getNextSegmentId(bizType);
            if (segmentId != null) {
                return segmentId;
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        throw new IdSysException("error query segmentId: " + message);
    }

    @Override
    public Long nextId() {
        while (true) {
            if (current == null) {
                loadCurrent();
                continue;
            }
            Result result = current.nextId();
            if (result.getCode() == ResultCode.OVER) {
                loadCurrent();
            } else {
                if (result.getCode() == ResultCode.LOADING) {
                    loadCurrent();
                }
                return result.getId();
            }
        }
    }

    @Override
    public List<Long> nextId(Integer batchSize) {
        return null;
    }

}
