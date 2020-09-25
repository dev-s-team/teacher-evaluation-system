package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.common.util.JwtTokenUtil;
import com.csmaxwell.tes.dao.TesRoleMapper;
import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesUserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    private TesRoleMapper tesRoleMapper;

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
        TesUser tesUser = new TesUser();
        tesUser.setUsername(username);
        return tesUserMapper.selectOne(tesUser);
    }

    @Override
    public TesUser register(TesUser tesUserParam) {
        TesUser tesUser = new TesUser();
        BeanUtils.copyProperties(tesUserParam, tesUser);
        tesUser.setStatus(1);
        // 查询是否具有相同用户名的用户
        Example example = new Example(TesUser.class);
        example.createCriteria().andEqualTo("username", tesUser.getUsername());
        List<TesUser> tesUserList = tesUserMapper.selectByExample(example);
        if (tesUserList.size() > 0) {
            return null;
        }
        // 将密码进行加密
        String encodePassword = passwordEncoder.encode(tesUser.getPassword());
        tesUser.setPassword(encodePassword);
        tesUserMapper.insertSelective(tesUser);
        return tesUser;
    }

    @Override
    public String login(String username, String password) {
        String token = null;

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }

        return token;
    }

    @Override
    public List<TesPermission> getPermissionList(Long userId) {
        return tesUserMapper.getPermissionList(userId);
    }

    public List<TesUser> findAll() {
        List<TesUser> userList = tesUserMapper.selectAll();
        return userList;
    }

    public TesUser findById(Integer id) {
        TesUser tesUser = tesUserMapper.selectByPrimaryKey(id);
        return tesUser;
    }

    @Override
    public int create(TesUser tesUserParam) {
        TesUser tesUser = new TesUser();
        BeanUtils.copyProperties(tesUserParam, tesUser);
        String encodePassword = passwordEncoder.encode(tesUser.getPassword());
        tesUser.setPassword(encodePassword);
        int count = tesUserMapper.insertSelective(tesUser);
        return count;
    }

    @Override
    public int delete(Long userId) {
        int count = tesUserMapper.deleteByPrimaryKey(userId);
        return count;
    }

    @Override
    public TesUser select(Long userId) {
        TesUser tesUser = tesUserMapper.selectByPrimaryKey(userId);
        return tesUser;
    }

    @Override
    public int update(Long userId, TesUser tesUser) {
        tesUser.setId(userId);
        int count = tesUserMapper.updateByPrimaryKeySelective(tesUser);
        return count;
    }

    @Override
    public List<TesUser> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andLike("username", "%" + keyword + "%");
            example.or(example.createCriteria().andLike("no", "%" + keyword + "%"));
        }
        List<TesUser> userList = tesUserMapper.selectByExample(example);
        return userList;
    }

    @Override
    public TesRole findRoleById(Long id) {
        TesUser tesUser = new TesUser();
        tesUser.setId(id);
        TesUser user = tesUserMapper.selectOne(tesUser);
        Example example = new Example(TesRole.class);
        example.createCriteria().andEqualTo("id", user.getRoleId());
        List<TesRole> tesRoles = tesRoleMapper.selectByExample(example);
        return tesRoles.get(0);
    }

    @Override
    public int updateRole(Long userId, Long roleId) {
        TesUser tesUser = new TesUser();
        tesUser.setRoleId(roleId);
        return update(userId, tesUser);
    }
}
