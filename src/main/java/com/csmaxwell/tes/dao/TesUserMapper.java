package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesUserMapper extends Mapper<TesUser> {
    List<TesPermission> getPermissionList(Long userId);
}