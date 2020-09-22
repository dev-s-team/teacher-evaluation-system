package com.csmaxwell.tes.service.impl;


import com.csmaxwell.tes.dao.TesSemesterMapper;
import com.csmaxwell.tes.domain.TesSemester;
import com.csmaxwell.tes.service.TesSemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesSemesterServiceImpl implements TesSemesterService {


    @Autowired
    private TesSemesterMapper tesSemesterMapper;

    @Override
    public int create(TesSemester tesSemesterParam) {
        int count = tesSemesterMapper.insertSelective(tesSemesterParam);
        return count;
    }




}
