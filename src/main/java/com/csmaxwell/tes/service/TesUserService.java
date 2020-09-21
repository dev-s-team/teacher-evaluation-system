package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;

import java.util.List;

/**
 * 用户Service
 * Created by maxwell on 2020/9/14.
 */
public interface TesUserService {

    // 根据用户名获取用户
    TesUser getUserByUsername(String username);

    // 注册
    TesUser register(TesUser tesUser);

    /**
     * 登录
     * @param username
     * @param password
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    // 获取用户的所有权限
    List<TesPermission> getPermissionList(Long userId);

    List<TesUser> findAll();

    TesUser findById(Integer id);

    int create(TesUser tesUserParam);

    int delete(Long userId);

    TesUser select(Long userId);

    int update(Long userId, TesUser tesUser);
}
