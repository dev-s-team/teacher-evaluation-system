package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesEvaluationControlMapper;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.service.TesEvaluationControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TesEvaluationControlServiceImpl implements TesEvaluationControlService {

    @Autowired
    private TesEvaluationControlMapper tesEvaluationControlMapper;


    @Override
    public TesEvaluationControl select(Long semesterId, TesEvaluationControl tesEvaluationControl) {
        tesEvaluationControl.setSemesterId(semesterId);
        TesEvaluationControl evlControl1 = tesEvaluationControlMapper.selectOne(tesEvaluationControl);

        return evlControl1;
    }

    @Override
    public List<TesEvaluationControl> tecList(Long semesterId) {
        Example example = new Example(TesEvaluationControl.class);
        example.createCriteria().andEqualTo("semesterId", semesterId);
        List<TesEvaluationControl> evalControls = tesEvaluationControlMapper.selectByExample(example);
//        TesEvaluationControl evalControl = evalControls.get(0);
//        System.out.println(evalControl);
        return evalControls;
    }
}
