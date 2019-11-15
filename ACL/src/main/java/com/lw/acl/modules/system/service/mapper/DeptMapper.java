package com.lw.acl.modules.system.service.mapper;

import com.lw.acl.base.BaseMapper;
import com.lw.acl.modules.system.domain.Dept;
import com.lw.acl.modules.system.service.dto.DeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDTO, Dept> {
}
