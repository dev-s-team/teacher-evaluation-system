package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesDepartmentMapper;
import com.csmaxwell.tes.domain.TesDepartment;
import com.csmaxwell.tes.service.TesDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesDepartmentServiceImpl implements TesDepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesDepartmentServiceImpl.class);

    @Autowired
    private TesDepartmentMapper tesDepartmentMapper;

    @Override
    public int create(TesDepartment tesDepartmentParam) {
        int count=tesDepartmentMapper.insertSelective(tesDepartmentParam);
        return count;
    }

    @Override
    public List<TesDepartment> selectAll() {
        return tesDepartmentMapper.selectAll();
    }

    @Override
    public int delete(Long departmentId) {
        int count=tesDepartmentMapper.deleteByPrimaryKey(departmentId);
        return count;
    }

    @Override
    public int update(Long departmentId, TesDepartment departmentDto) {
        departmentDto.setId(departmentId);
        int count=tesDepartmentMapper.updateByPrimaryKeySelective(departmentDto);
        return count;
    }
}
