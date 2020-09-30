package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesUserMapper extends Mapper<TesUser> {

    @Select("SELECT tp.* FROM tes_user tu, tes_role tr, tes_role_permission trp, tes_permission tp WHERE tu.role_id = tr.id and tr.id = trp.role_id and trp.permission_id = tp.id and tu.id = #{userId}")
    List<TesPermission> getPermissionList(Long userId);

}
