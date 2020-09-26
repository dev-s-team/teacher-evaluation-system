package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesDepartment;

import java.util.List;

public interface TesDepartmentService {
    int create(TesDepartment tesDepartmentParam);

    List<TesDepartment> selectAll(String keyword, Integer pageSize, Integer pageNum);

    int delete(Long departmentId);

    int update(Long departmentId, TesDepartment departmentDto);
}
