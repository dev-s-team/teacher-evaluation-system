package com.csmaxwell.tes.dao;

import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesPermission;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TesMenuMapper extends Mapper<TesMenu> {

    @Update("update tes_menu set hidden= case when hidden=1 then 0 else 1 end where id = #{id}")
    int updateHidden(Long id);
}