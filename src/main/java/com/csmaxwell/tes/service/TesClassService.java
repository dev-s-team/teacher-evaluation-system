package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesClass;

import java.util.List;


public interface TesClassService{

    int create(TesClass tesClassParam);

    int delete(Long classId);

    TesClass select(Long classId);

    int update(Long classId, TesClass tesClass);

    List<TesClass> findAll(String keyword, Integer pageSize, Integer pageNum);

    List<TesClass> all();

    int selectCountClass();

    int[] classAllUser();

    int[] classAllUserComplete();
}
