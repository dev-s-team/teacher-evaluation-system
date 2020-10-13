package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesEvaluationMapper;
import com.csmaxwell.tes.dao.TesEvaluationControlMapper;
import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.service.TesEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesEvaluationServiceImpl implements TesEvaluationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesEvaluationServiceImpl.class);

    @Autowired
    private TesEvaluationMapper tesEvaluationMapper;
    @Autowired
    private TesEvaluationControlMapper tesEvaluationControlMapper;
    @Autowired
    private TesUserMapper tesUserMapper;


    @Override
    public int updateById(Long evaluationControlId) {

        Example example = new Example(TesEvaluationControl.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", evaluationControlId);
        criteria.andNotEqualTo("status", 1);
        TesEvaluationControl tesEvaluationControl = new TesEvaluationControl();
        tesEvaluationControl.setStatus(1);
        int count = tesEvaluationControlMapper.updateByExampleSelective(tesEvaluationControl, example);

        return count;
    }

    @Override
    public List<TesEvaluation> select() {
        List<TesEvaluation> tesEvaluations = tesEvaluationMapper.selectAll();
        return tesEvaluations;
    }


    @Override
    public List<TesEvaluation> teList(Long id, Long roleId) {
        Example example = new Example(TesEvaluation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("evalCnotrolId", id);
        criteria.andEqualTo("roleId", roleId);

        List<TesEvaluation> evaluations = tesEvaluationMapper.selectByExample(example);
        return evaluations;
    }

    @Override
    public List<Integer> findBySmsIdAndRoleId(Long evalControlId, Long roleId) {
        Example example = new Example(TesEvaluation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("evalCnotrolId", evalControlId);
        criteria.andEqualTo("roleId", roleId);
        List<TesEvaluation> evaluationList = tesEvaluationMapper.selectByExample(example);
        List<Integer> list = new ArrayList<>();
        for (TesEvaluation eval : evaluationList) {
            list.add(Math.toIntExact(eval.getIndicatorId()));
        }

        return list;
    }

}
