package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesPermission;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesEvaluationControlMapper extends Mapper<TesEvaluationControl> {
    // TODO 有SQL语句
    @Update("update tes_evaluation_control set status = 1  where id = #{evaluationControlId} and status != 1")
    int updateById(Long evaluationControlId);
}