package com.lw.acl.modules.system.rest;

import com.lw.acl.exception.BadRequestException;
import com.lw.acl.log.aop.log.Log;
import com.lw.acl.modules.system.domain.Dept;
import com.lw.acl.modules.system.service.DeptService;
import com.lw.acl.modules.system.service.dto.DeptDTO;
import com.lw.acl.modules.system.service.dto.DeptQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "系统：部门管理")
@RequestMapping("/api/dept")
public class DeptController {

    private static final String ENTITY_NAME = "dept";

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @Log(value = "新增部门")
    @ApiOperation(value = "新增部门")
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Dept dept) {
        if (dept.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity<>(deptService.create(dept), HttpStatus.CREATED);
    }

    @Log(value = "查询部门")
    @ApiOperation(value = "查询部门")
    @GetMapping
    public ResponseEntity getDepts(DeptQueryCriteria criteria) {
        List<DeptDTO> deptDTOList = deptService.queryAll(criteria);
        return new ResponseEntity<>(deptService.buildTree(deptDTOList), HttpStatus.CREATED);
    }

}
