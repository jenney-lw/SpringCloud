package com.lw.acl.dto;

import com.google.common.collect.Lists;
import com.lw.acl.model.SysDept;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept sysDept) {
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(sysDept, deptLevelDto);
        return deptLevelDto;
    }

    public List<DeptLevelDto> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptLevelDto> deptList) {
        this.deptList = deptList;
    }
}
