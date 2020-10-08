package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesEvaluationResult;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/26.
 */
public interface TesEvaluationResultService {
    int create(TesEvaluationResult tesEvaluationResult);

    List<TesEvaluationResult> list(String keyword, Integer pageSize, Integer pageNum);

    List<TesEvaluationResult> select(Long userId, Integer pageSize, Integer pageNum);

    int commit(Long userId, Long roleId, Long targetId, Long courseId, List<String> radios, List<String> weights);
}
