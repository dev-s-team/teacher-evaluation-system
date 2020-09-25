package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesEvaluationMapper;
import com.csmaxwell.tes.dao.TesEvaluationControlMapper;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesEvaluationServiceImpl implements TesEvaluationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesEvaluationServiceImpl.class);

    @Autowired
    private TesEvaluationMapper tesEvaluationMapper;

    @Autowired
    private TesEvaluationControlMapper tesEvaluationControlMapper;

    @Override
    public int updateById(Long evaluationControlId) {
        int count = tesEvaluationControlMapper.updateById(evaluationControlId);
        return count;
    }

    @Override
    public List<TesEvaluation> select() {
        List<TesEvaluation> tesEvaluations = tesEvaluationMapper.selectAll();
        return tesEvaluations;
    }
}
