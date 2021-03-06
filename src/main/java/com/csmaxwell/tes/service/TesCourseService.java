package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.domain.TesUser;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/15.
 */
public interface TesCourseService {
    List<TesCourse> listAllCourse(String keyword, Integer pageSize, Integer pageNum);

    int create(TesCourse tesCourseParam);

    TesCourse findById(Long id);

    int update(Long id, TesCourse tesCourseDto);

    int delete(Long id);

    TesUser findUserInfoById(String id);

    int getCount(String num);

    TesCourse findByNum(String courseNum);
}
