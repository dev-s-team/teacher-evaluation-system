package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesPermissionMapper;
import com.csmaxwell.tes.dao.TesRolePermissionMapper;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesRolePermission;
import com.csmaxwell.tes.service.TesPermissionService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TesPromissionService实现类
 * Created by maxwell on 2020/9/14.
 */
@Service
public class TesPermissionServiceImpl implements TesPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesPermissionServiceImpl.class);

    @Autowired
    private TesPermissionMapper tesPermissionMapper;
    @Autowired
    private TesRolePermissionMapper tesRolePermissionMapper;

    @Override
    public TesPermission select(Long permissionId) {
        TesPermission tesPermission = tesPermissionMapper.selectByPrimaryKey(permissionId);
        return tesPermission;
    }

    @Override
    public List<TesPermission> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesPermission.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andLike("name", "%" + keyword + "%");
            example.or(example.createCriteria().andLike("permission", "%" + keyword + "%"));
        }
        List<TesPermission> promissionList = tesPermissionMapper.selectByExample(example);
        return promissionList;
    }

    @Override
    public TesPermission addPermission(TesPermission tesPermissionParam) {
        TesPermission tesPermission = new TesPermission();
        BeanUtils.copyProperties(tesPermissionParam, tesPermission);
        tesPermission.setStatus(1);
        // 查询是否具有相同用户名的用户
        Example example = new Example(TesPermission.class);
        example.createCriteria().andEqualTo("name", tesPermission.getName());
        List<TesPermission> tesPermissionsList = tesPermissionMapper.selectByExample(example);
        if (tesPermissionsList.size() > 0) {
            return null;
        }
        // 将密码进行加密
        tesPermissionMapper.insertSelective(tesPermission);
        return tesPermission;
    }

    @Override
    public int delete(Long permissionId) {
        int count = tesPermissionMapper.deleteByPrimaryKey(permissionId);
        return count;
    }

    @Override
    public int deleteSelect(Long id) {
        Example example = new Example(TesRolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("permissionId", id);
        int count = tesRolePermissionMapper.selectCountByExample(example);
        return count;
    }

    @Override
    public int update(Long id,TesPermission tesPermission) {
        tesPermission.setId(id);
        int count = tesPermissionMapper.updateByPrimaryKeySelective(tesPermission);
        return count;
    }
}
