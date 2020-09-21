package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesClass;


public interface TesClassService{

    int create(TesClass tesClassParam);

    int delete(Long classId);

    TesClass select(Long classId);

    int update(Long classId, TesClass tesClass);
}
