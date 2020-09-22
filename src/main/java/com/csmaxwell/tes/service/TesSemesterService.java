package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesSemester;

public interface TesSemesterService {
    int create(TesSemester tesSemesterParam);

    TesSemester select(Long semesterId);

    int update(Long semesterId, TesSemester tesSemester);
}
