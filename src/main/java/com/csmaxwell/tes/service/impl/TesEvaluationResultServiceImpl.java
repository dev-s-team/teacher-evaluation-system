package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesEvaluationResultMapper;
import com.csmaxwell.tes.domain.TesEvaluationResult;
import com.csmaxwell.tes.service.TesEvaluationResultService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/26.
 */
@Service
public class TesEvaluationResultServiceImpl implements TesEvaluationResultService {

    @Autowired
    private TesEvaluationResultMapper tesEvaluationResultMapper;


    @Override
    public int create(TesEvaluationResult tesEvaluationResult) {
        int count = tesEvaluationResultMapper.insertSelective(tesEvaluationResult);
        return count;
    }

    @Override
    public List<TesEvaluationResult> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesEvaluationResult.class);
        // if (!StringUtils.isEmpty(keyword)) {
        //     example.createCriteria()
        // }
        return tesEvaluationResultMapper.selectByExample(example);
    }

    @Override
    public List<TesEvaluationResult> select(Long userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesEvaluationResult.class);
        example.createCriteria().andEqualTo("userId", userId);
        return tesEvaluationResultMapper.selectByExample(example);
    }


}
