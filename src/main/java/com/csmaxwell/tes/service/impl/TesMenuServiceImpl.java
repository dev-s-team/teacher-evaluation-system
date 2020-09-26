package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesMenuMapper;
import com.csmaxwell.tes.domain.TesClass;
import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.service.TesMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/26.
 */
@Service
public class TesMenuServiceImpl implements TesMenuService {

    @Autowired
    private TesMenuMapper tesMenuMapper;

    @Override
    public int create(TesMenu tesMenu) {
        return tesMenuMapper.insertSelective(tesMenu);
    }

    @Override
    public int delete(Long id) {
        return tesMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Long id, TesMenu tesMenu) {
        tesMenu.setId(id);
        return tesMenuMapper.updateByPrimaryKeySelective(tesMenu);
    }

    @Override
    public List<TesMenu> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andLike("title", "%" + keyword + "%");
        }
        return tesMenuMapper.selectByExample(example);
    }

    @Override
    public TesMenu select(Long id) {
        TesMenu tesMenu = new TesMenu();
        tesMenu.setId(id);
        return tesMenuMapper.selectOne(tesMenu);
    }
}
