package com.lw.acl.controller;

import com.lw.acl.common.JsonData;
import com.lw.acl.dto.DeptLevelDto;
import com.lw.acl.parma.DeptParam;
import com.lw.acl.service.SysDeptService;
import com.lw.acl.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(@RequestBody DeptParam param) throws Exception {
        sysDeptService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> deptLevelDtoList = sysTreeService.deptTree();
        return JsonData.success(deptLevelDtoList);
    }

}
