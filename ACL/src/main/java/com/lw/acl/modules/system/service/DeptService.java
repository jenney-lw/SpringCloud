package com.lw.acl.modules.system.service;

import com.lw.acl.modules.system.domain.Dept;
import com.lw.acl.modules.system.service.dto.DeptDTO;
import com.lw.acl.modules.system.service.dto.DeptQueryCriteria;

import java.util.List;

public interface DeptService {

    DeptDTO create(Dept dept);

    List<DeptDTO> queryAll(DeptQueryCriteria criteria);

    Object buildTree(List<DeptDTO> deptDTOS);

}
