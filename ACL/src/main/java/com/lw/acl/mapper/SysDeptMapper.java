package com.lw.acl.mapper;

import com.lw.acl.model.SysDept;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SysDeptMapper {

    private static Map<Integer, SysDept> SYS_DEPT_DB = new HashMap<>();

    public void save(SysDept sysDept) {
        SYS_DEPT_DB.put(sysDept.getId(), sysDept);
    }

    public SysDept findByDeptId(Integer deptId) {
        return SYS_DEPT_DB.get(deptId);
    }

    public List<SysDept> getAllDept() {
        List<SysDept> sysDeptList = new ArrayList<>();
        for (Map.Entry<Integer, SysDept> entry : SYS_DEPT_DB.entrySet()) {
            sysDeptList.add(entry.getValue());
        }
        return sysDeptList;
    }
}
