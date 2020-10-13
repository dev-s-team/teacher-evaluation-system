package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesIndicator;

import java.util.List;

public interface TesIndicatorService {
    int create(TesIndicator tesindicatorParam);

    int delete(Long indicatorId);

    TesIndicator select(Long indicatorId);

    int update(Long indicatorId, TesIndicator tesindicator);

    List<TesIndicator> select();

    List<TesIndicator> list(String keyword, Integer pageSize, Integer pageNum);

    TesIndicator indicatorList(Long indicatorId);

    int updateStatus(Long id, Byte status);

    List<TesIndicator> all();
}
