package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.domain.TesRoleMenu;
import com.csmaxwell.tes.domain.TesRolePermission;

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

    int insertMenu(Long roleId, Long menuIds);

    int delRoleMenu(Long roleId);

    List<TesRolePermission> listRolePermission(Long roleId);

    int delRolePermission(Long roleId);

    int insertPermission(Long roleId, Long permissionId);
}
