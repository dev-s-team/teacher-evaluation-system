package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesClassMapper;
import com.csmaxwell.tes.dao.TesEvaluationResultMapper;
import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesClass;
import com.csmaxwell.tes.domain.TesEvaluationResult;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesClassService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesClassServiceImpl implements TesClassService {

    @Autowired
    private TesClassMapper tesClassMapper;

    @Autowired
    private TesEvaluationResultMapper tesEvaluationResultMapper;

    @Autowired
    private TesUserMapper tesUserMapper;

    @Override
    public int create(TesClass tesClassParam) {
        int count = tesClassMapper.insertSelective(tesClassParam);
        return count;
    }

    @Override
    public int delete(Long classId) {
        int count = tesClassMapper.deleteByPrimaryKey(classId);
        return count;
    }

    @Override
    public TesClass select(Long classId) {
        TesClass tesClass = tesClassMapper.selectByPrimaryKey(classId);
        return tesClass;
    }

    @Override
    public int update(Long classId, TesClass tesClass) {
        tesClass.setId(classId);
        int count = tesClassMapper.updateByPrimaryKeySelective(tesClass);
        return count;
    }

    @Override
    public List<TesClass> findAll(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesClass.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andEqualTo("no", keyword);
        }
        List<TesClass> classList = tesClassMapper.selectByExample(example);
        return classList;
    }

    @Override
    public List<TesClass> all() {
        return tesClassMapper.selectAll();
    }

    @Override
    public int selectCountClass(){
        Example example = new Example(TesClass.class);
        int count = tesClassMapper.selectCountByExample(example);
        return count;
    }

//    查询每个班级的所有人数
    public  int[] classAllUser(){
//        List<TesClass> tesClassList = tesClassMapper.selectAll();
        Example example = new Example(TesClass.class);
        int length = tesClassMapper.selectCountByExample(example);
        int[] classAllUserList = new int[length];
        List<TesClass> tesClassList = tesClassMapper.selectByExample(example);
        int i = 0;
        for (TesClass tesClass : tesClassList) {
            Example example1 = new Example(TesUser.class);
            Example.Criteria criteria = example1.createCriteria();
            criteria.andEqualTo("classNo",tesClass.getNo());
            int count = tesUserMapper.selectCountByExample(example1);
//            for (int i=0;i<length;i++){
//                classAllUserList[i] = count;
//            }
            classAllUserList[i] = count;
            i++;
        }
        return classAllUserList;
    }

    /**
     * 查询所有班级号
     * @return
     */
    public int[] classAllUserComplete() {
        Example example = new Example(TesClass.class);
        List<TesClass> tesClassList = tesClassMapper.selectByExample(example);
        int length = tesClassMapper.selectCountByExample(example);
        int[] allClass = new int[length];
        int i = 0;
        for (TesClass tesClass : tesClassList) {
            allClass[i] = Integer.valueOf(tesClass.getNo()).intValue();
            i++;
        }
        return allClass;
    }

}
