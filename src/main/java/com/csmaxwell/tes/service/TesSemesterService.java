package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesSemester;
import com.csmaxwell.tes.dto.TesSemesterDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TesSemesterService {

    TesSemester select(Long semesterId);

    int update(Long semesterId, TesSemester tesSemester);

    PageInfo<TesSemesterDto> list(Integer pageSize, Integer pageNum);

    int delete(Long id);

    int create(String name, Integer status, List<String> studentIds, List<String> teacherIds, List<String> leaderIds);

    int updateEval(List<String> studentIds, List<String> teacherIds, List<String> leaderIds, String name, Integer semesterId);

    int deleteEval(List<String> studentIds, List<String> teacherIds, List<String> leaderIds, Integer semesterId);
}
