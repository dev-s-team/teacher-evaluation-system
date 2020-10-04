package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesMenu;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/26.
 */
public interface TesMenuService {
    int create(TesMenu tesMenu);

    int delete(Long id);

    int update(Long id, TesMenu tesMenu);

    List<TesMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    TesMenu select(Long id);

    int updateHidden(Long id, TesMenu tesMenu);

    List<TesMenu> secondList(Long id, Integer pageSize, Integer pageNum);
}
