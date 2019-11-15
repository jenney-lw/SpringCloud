package com.lw.acl.service;

import com.lw.acl.mapper.SysDeptMapper;
import com.lw.acl.model.SysDept;
import com.lw.acl.parma.DeptParam;
import com.lw.acl.utils.LevelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) throws Exception {
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new Exception("同一层级下存在相同的部门名称");
        }

        SysDept sysDept = new SysDept();
        sysDept.setId(param.getId());
        sysDept.setName(param.getName());
        sysDept.setParentId(param.getParentId());
        sysDept.setSeq(param.getSeq());
        sysDept.setRemark(param.getRemark());
        sysDept.setLevel(LevelUtils.calculateLevel(getLevel(param.getParentId()), param.getParentId()));

        sysDeptMapper.save(sysDept);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return false;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.findByDeptId(deptId);
        if (sysDept == null) {
            return null;
        }
        return sysDept.getLevel();
    }

}
