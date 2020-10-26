package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesRoleMapper extends Mapper<TesRole> {

    List<TesMenu> getMenuList(Long id);

}