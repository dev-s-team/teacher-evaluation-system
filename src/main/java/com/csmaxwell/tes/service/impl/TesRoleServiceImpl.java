package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesRoleMapper;
import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.service.TesRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int deleteRelation(Long roleId) {
        // 删除角色权限表中数据
        Example example1 = new Example(TesRolePermission.class);
        example1.createCriteria().andEqualTo("roleId", roleId);
        int count1 = tesRolePermissionMapper.deleteByExample(example1);

        // 删除角色菜单表中数据
        Example example2 = new Example(TesRoleMenu.class);
        example2.createCriteria().andEqualTo("roleId", roleId);
        int count2 = tesRoleMenuMapper.deleteByExample(example2);

        // 删除角色表中角色
        int count3 = tesRoleMapper.deleteByPrimaryKey(roleId);

        return count3;
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
