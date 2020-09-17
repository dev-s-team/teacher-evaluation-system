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

    @Override
    public int create(TesCourse tesCourseParam) {
        int count = tesCourseMapper.insertSelective(tesCourseParam);
        return count;
    }

    @Override
    public TesCourse findById(Long id) {
        TesCourse tesCourse = new TesCourse();
        tesCourse.setId(id);
        return tesCourseMapper.selectOne(tesCourse);
    }

    @Override
    public int update(Long id, TesCourse tesCourseDto) {
        tesCourseDto.setId(id);
        return tesCourseMapper.updateByPrimaryKeySelective(tesCourseDto);
    }

    @Override
    public int delete(Long id) {
        TesCourse tesCourse = new TesCourse();
        tesCourse.setId(id);
        return tesCourseMapper.deleteByPrimaryKey(tesCourse);
    }
}
