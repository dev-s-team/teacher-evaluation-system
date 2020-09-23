package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesUser;

import java.util.List;

public interface TesEvaluationService {


    int updateById(Long evaluationControlId);

    List<TesEvaluation> select();
}
