package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.common.constant.EvalOption;
import com.csmaxwell.tes.dao.TesEvaluationResultMapper;
import com.csmaxwell.tes.domain.TesEvaluationResult;
import com.csmaxwell.tes.service.TesEvaluationResultService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public int commit(Long userId, Long roleId, Long targetId, Long courseId, List<String> radios
            , List<String> weights) {

        // 将所有指标权重相加
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal weightSum = new BigDecimal("0.00");
        for (String weight : weights) {
            BigDecimal bd = new BigDecimal(weight);
            weightSum = weightSum.add(bd);
        }
        BigDecimal score = new BigDecimal("0.00");
        for (int i = 0; i < weights.size(); i++) {
            BigDecimal decimal = hundred.divide(weightSum, 2, RoundingMode.UP);// 保留2位小数，并四舍五入
            BigDecimal weight = new BigDecimal(weights.get(i));
            BigDecimal radio = EvalOption.options.get(Integer.parseInt(radios.get(i)));
            decimal = decimal.multiply(weight);
            decimal = decimal.multiply(radio);
            System.out.println(decimal);
            score = score.add(decimal);
        }
        System.out.println("评教成绩为: " + score);

        TesEvaluationResult result = new TesEvaluationResult();
        result.setUserId(userId);
        result.setRoleId(roleId);
        result.setTargetId(targetId);
        result.setCourseId(courseId);
        result.setScore(score);

        return create(result);
    }


}
