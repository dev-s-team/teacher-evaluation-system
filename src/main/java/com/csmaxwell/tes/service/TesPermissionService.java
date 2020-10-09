package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesRolePermission;
import com.csmaxwell.tes.domain.TesUser;

import java.util.List;

public interface TesPermissionService {
    TesPermission select(Long permissionId);

    List<TesPermission> list(String keyword, Integer pageSize, Integer pageNum);

    TesPermission addPermission(TesPermission tesPermission);

    int delete(Long permissionId);

    int deleteSelect(Long id);

    int update(Long id,TesPermission tesPermission);
}
