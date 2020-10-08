package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesSemester;

import java.util.List;

public interface TesSemesterService {
    int create(TesSemester tesSemesterParam);

    TesSemester select(Long semesterId);

    int update(Long semesterId, TesSemester tesSemester);

    List<TesSemester> list();
}
