package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesIndicatorMapper;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesIndicatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesIndicatorServiceImpl  implements TesIndicatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesIndicatorServiceImpl.class);

    @Autowired
    private TesIndicatorMapper tesIndicatorMapper;

    @Override
    public int create(TesIndicator tesindicatorParam) {
        int count = tesIndicatorMapper.insertSelective(tesindicatorParam);
        return count;
    }

    @Override
    public int delete(Long indicatorId) {
        int count = tesIndicatorMapper.deleteByPrimaryKey(indicatorId);
        return count;
    }

    @Override
    public TesIndicator select(Long indicatorId) {
        TesIndicator tesIndicator = tesIndicatorMapper.selectByPrimaryKey(indicatorId);
        return tesIndicator;
    }

    @Override
    public int update(Long indicatorId, TesIndicator tesindicator) {
        tesindicator.setId(indicatorId);
        int count = tesIndicatorMapper.updateByPrimaryKeySelective(tesindicator);
        return count;
    }

    @Override
    public List<TesIndicator> select() {
        List<TesIndicator> tesIndicators = tesIndicatorMapper.selectAll();

        return tesIndicators;

    }
}
