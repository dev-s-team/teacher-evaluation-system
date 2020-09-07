package com.csmaxwell.tes.service;

import com.csmaxwell.tes.mapper.UserMapper;
import com.csmaxwell.tes.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * S
 * Created by maxwell on 2020/9/7.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id查询
     */
    public User findById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 新增保存用户
     */
    @Transactional
    public void saveUser(User user) {
        // 选择性新增，如果属性为空则该属性不会出现在insert语句中
        userMapper.insertSelective(user);
    }
}
