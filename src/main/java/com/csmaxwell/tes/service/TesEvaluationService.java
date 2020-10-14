package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesEvaluation;

import java.util.List;

public interface TesEvaluationService {


    int updateById(Long evaluationControlId);

    List<TesEvaluation> select();


    List<TesEvaluation> teList(Long id, Long roleId);

    List<Integer> findBySmsIdAndRoleId(Long evalControlId, Long roleId);
}
