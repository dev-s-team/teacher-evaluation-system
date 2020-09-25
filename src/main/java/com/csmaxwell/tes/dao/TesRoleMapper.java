package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesRoleMapper extends Mapper<TesRole> {

    /**
     * 根据用户id获取菜单列表
     * @param id
     * @return
     */
    List<TesMenu> getMenuList(Long id);
}