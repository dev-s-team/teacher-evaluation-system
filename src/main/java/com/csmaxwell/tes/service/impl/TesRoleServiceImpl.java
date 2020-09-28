package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesRoleMapper;
import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.service.TesRoleService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TesRoleService实现类
 * Created by yl on 2020/9/16.
 */
@Service
public class TesRoleServiceImpl implements TesRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesRoleServiceImpl.class);

    @Autowired
    private TesRoleMapper tesRoleMapper;

    @Override
    public List<TesRole> selectAll() {
        List<TesRole> roleList = tesRoleMapper.selectAll();
        return roleList;
    }

    @Override
    public int delete(Long roleId) {
        int count=tesRoleMapper.deleteByPrimaryKey(roleId);
        return count;
    }

    @Override
    public int update(Long roleId, TesRole tesRoleDto) {
        tesRoleDto.setId(roleId);
        return tesRoleMapper.updateByPrimaryKeySelective(tesRoleDto);
    }

    @Override
    public int create(TesRole tesRoleParam) {
        int count=tesRoleMapper.insertSelective(tesRoleParam);
        return count;
    }

    @Override
    public List<TesMenu> getMenuList(Long id) {
        return tesRoleMapper.getMenuList(id);
    }

    @Override
    public TesRole findById(Long id) {
        TesRole tesRole = new TesRole();
        tesRole.setId(id);
        return tesRoleMapper.selectOne(tesRole);
    }

    @Override
    public List<TesRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andLike("name", "%" + keyword + "%");
//            example.or(example.createCriteria().andLike("no", "%" + keyword + "%"));
        }
        List<TesRole> roleList = tesRoleMapper.selectByExample(example);
        return roleList;
    }

}
