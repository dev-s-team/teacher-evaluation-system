package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.common.util.JwtTokenUtil;
import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TesUserService实现类
 * Created by maxwell on 2020/9/14.
 */
@Service
public class TesUserServiceImpl implements TesUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesUserServiceImpl.class);

    @Autowired
    private TesUserMapper tesUserMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public TesUser getUserByUsername(String username) {
        tesUserMapper.
        return null;
    }

    @Override
    public TesUser register(TesUser tesUser) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<TesPermission> getPermissionList(Long userId) {
        return null;
    }

    public List<TesUser> findAll() {
        List<TesUser> userList = tesUserMapper.selectAll();
        return userList;
    }

    public TesUser findById(Integer id) {
        TesUser tesUser = tesUserMapper.selectByPrimaryKey(id);
        return tesUser;
    }
}
