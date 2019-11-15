package com.lw.acl.modules.system.service.impl;

import com.lw.acl.modules.system.domain.Dept;
import com.lw.acl.modules.system.repository.DeptRepository;
import com.lw.acl.modules.system.service.DeptService;
import com.lw.acl.modules.system.service.dto.DeptDTO;
import com.lw.acl.modules.system.service.dto.DeptQueryCriteria;
import com.lw.acl.modules.system.service.mapper.DeptMapper;
import com.lw.acl.utils.QueryHelp;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "dept")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptRepository deptRepository, DeptMapper deptMapper) {
        this.deptRepository = deptRepository;
        this.deptMapper = deptMapper;
    }

    @Override
    public DeptDTO create(Dept dept) {
        return deptMapper.toDTO(deptRepository.save(dept));
    }

    @Override
    @Cacheable
    public List<DeptDTO> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDtoList(deptRepository.findAll(((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder))));
    }

    @Override
    @Cacheable
    public Object buildTree(List<DeptDTO> deptDTOS) {
        Set<DeptDTO> trees = new LinkedHashSet<>();
        Set<DeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptDTO::getName).collect(Collectors.toList());
        boolean isChild;

        for (DeptDTO deptDTO : deptDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (DeptDTO dept : deptDTOS) {
                if (dept.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<>());
                    }
                    deptDTO.getChildren().add(dept);
                }
            }

            if (isChild) {
                depts.add(deptDTO);
            }
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDTOS.size();

        Map<String, Object> map = new HashMap<>();
        map.put("totalElements", totalElements);
        map.put("content", CollectionUtils.isEmpty(trees) ? deptDTOS : trees);
        return map;
    }
}
