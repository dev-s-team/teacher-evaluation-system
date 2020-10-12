package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.*;

import java.util.List;

/**
 * 用户Service
 * Created by maxwell on 2020/9/14.
 */
public interface TesUserService {

    // 根据用户名获取用户
    TesUser getUserByNo(String no);

    // 注册
    TesUser register(TesUser tesUser);

    /**
     * 登录
     * @param no
     * @param password
     * @return 生成的JWT的token
     */
    String login(String no, String password);

    // 获取用户的所有权限
    List<TesPermission> getPermissionList(Long userId);

    List<TesUser> findAll();

    TesUser findById(Long id);

    int create(TesUser tesUserParam);

    int delete(Long userId);

    TesUser select(Long userId);

    int update(Long userId, TesUser tesUser);

    List<TesUser> list(String keyword, Integer pageSize, Integer pageNum);

    TesRole findRoleById(Long id);

    int updateRole(Long userId, Long roleId);

    List<TesCourse> findCourseListById(Long userId);

    TesClass findClassById(Long userId);

    TesDepartment findDeptById(Long userId);

    TesSemester findSemesterById(Long userId);

    TesUser findByNo(String no);

    void add(List<TesUser> data);

    int countStudent();

    int countTeacher();

    int countClass();

    int countDepartment();
}
