package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesRole;

import java.util.List;

public interface TesRoleService {

    List<TesRole> selectAll();

    int delete(Long roleId);

    int update(Long roleId, TesRole tesRoleDto);

    int create(TesRole tesRoleParam);
}
