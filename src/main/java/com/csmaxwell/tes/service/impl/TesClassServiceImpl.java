package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesClassMapper;
import com.csmaxwell.tes.domain.TesClass;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesClassServiceImpl implements TesClassService {

    @Autowired
    private TesClassMapper tesClassMapper;

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
    public List<TesClass> findAll() {
        List<TesClass> classList = tesClassMapper.selectAll();
        return classList;
    }
}
