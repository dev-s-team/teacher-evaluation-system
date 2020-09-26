package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesDepartmentMapper;
import com.csmaxwell.tes.domain.TesDepartment;
import com.csmaxwell.tes.service.TesDepartmentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    public List<TesDepartment> selectAll(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andEqualTo("no", keyword);
            example.or(example.createCriteria().andLike("name", "%" + keyword + "%"));
        }
        return tesDepartmentMapper.selectByExample(example);
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
