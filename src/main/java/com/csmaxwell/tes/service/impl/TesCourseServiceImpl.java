package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesCourseMapper;
import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.service.TesCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/15.
 */
@Service
public class TesCourseServiceImpl implements TesCourseService {

    @Autowired
    private TesCourseMapper tesCourseMapper;

    @Override
    public List<TesCourse> listAllCourse() {
        return tesCourseMapper.selectAll();
    }
}
