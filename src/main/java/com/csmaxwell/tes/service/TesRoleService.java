package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.domain.TesRoleMenu;

import java.util.List;

public interface TesRoleService {

    List<TesRole> selectAll();

    int delete(Long roleId);

    int update(Long roleId, TesRole tesRoleDto);

    int create(TesRole tesRoleParam);

    List<TesMenu> getMenuList(Long id);

    TesRole findById(Long id);

    int deleteRelation(Long roleId);

    List<TesRole> list(String keyword, Integer pageSize, Integer pageNum);

    int updateStatus(Long id, TesRole tesRole);

    List<TesRoleMenu> listRoleMenu(Long roleId);
}
