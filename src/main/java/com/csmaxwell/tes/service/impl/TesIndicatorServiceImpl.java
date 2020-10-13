package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesIndicatorMapper;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesIndicatorService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<TesIndicator> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesIndicator.class);
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andLike("name", "%" + keyword + "%");
        }
        return tesIndicatorMapper.selectByExample(example);
    }

    @Override
    public TesIndicator indicatorList(Long indicatorId) {

        Example example = new Example(TesIndicator.class);
        example.createCriteria().andEqualTo("id", indicatorId);
        List<TesIndicator> indicators = tesIndicatorMapper.selectByExample(example);
        return indicators.get(0);
    }

    @Override
    public int updateStatus(Long id, Byte status) {
        TesIndicator indicator = new TesIndicator();
        indicator.setId(id);
        indicator.setStatus(status);
        int count = tesIndicatorMapper.updateByPrimaryKeySelective(indicator);
        return count;
    }

    @Override
    public List<TesIndicator> all() {
        return tesIndicatorMapper.selectAll();
    }
}
