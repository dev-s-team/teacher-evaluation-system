package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.common.util.JwtTokenUtil;
import com.csmaxwell.tes.dao.*;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.dto.TesUserDetails;
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

import java.util.ArrayList;
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
    private TesCourseMapper tesCourseMapper;
    @Autowired
    private TesUserCourseMapper tesUserCourseMapper;
    @Autowired
    private TesClassMapper tesClassMapper;
    @Autowired
    private TesDepartmentMapper tesDepartmentMapper;
    @Autowired
    private TesSemesterMapper tesSemesterMapper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public TesUser getUserByNo(String no) {
        TesUser tesUser = new TesUser();
        tesUser.setNo(no);
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
    public String login(String no, String password) {
        String token = null;

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(no);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken((TesUserDetails) userDetails);
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

    public TesUser findById(Long id) {
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
        TesUser tesUser = findById(id);
        Example example = new Example(TesRole.class);
        example.createCriteria().andEqualTo("id", tesUser.getRoleId());
        List<TesRole> tesRoles = tesRoleMapper.selectByExample(example);
        return tesRoles.get(0);
    }

    @Override
    public int updateRole(Long userId, Long roleId) {
        TesUser tesUser = new TesUser();
        tesUser.setRoleId(roleId);
        return update(userId, tesUser);
    }

    @Override
    public List<TesCourse> findCourseListById(Long userId) {

        TesUser tesUser = findById(userId);

        List<TesCourse> courseList = new ArrayList<>();

        Example example1 = new Example(TesUserCourse.class);
        example1.createCriteria().andEqualTo("userNo", tesUser.getNo());
        List<TesUserCourse> userCourseList = tesUserCourseMapper.selectByExample(example1);

        for (TesUserCourse userCourse : userCourseList) {
            Example example2 = new Example(TesCourse.class);
            example2.createCriteria().andEqualTo("num", userCourse.getCourseNum());
            example2.createCriteria().andEqualTo("semesterId", tesUser.getSemesterId());
            courseList.add(tesCourseMapper.selectByExample(example2).get(0));
        }

        return courseList;
    }

    @Override
    public TesClass findClassById(Long userId) {
        TesUser tesUser = findById(userId);
        Example example = new Example(TesClass.class);
        example.createCriteria().andEqualTo("no", tesUser.getClassNo());
        List<TesClass> classList = tesClassMapper.selectByExample(example);
        return classList.get(0);
    }

    @Override
    public TesDepartment findDeptById(Long userId) {
        TesUser tesUser = findById(userId);
        Example example = new Example(TesDepartment.class);
        example.createCriteria().andEqualTo("no", tesUser.getDeptNo());
        List<TesDepartment> departmentList = tesDepartmentMapper.selectByExample(example);
        return departmentList.get(0);
    }

    @Override
    public TesSemester findSemesterById(Long userId) {
        TesUser tesUser = findById(userId);
        Example example = new Example(TesSemester.class);
        example.createCriteria().andEqualTo("id", tesUser.getSemesterId());
        List<TesSemester> semesterList = tesSemesterMapper.selectByExample(example);
        return semesterList.get(0);
    }

    @Override
    public TesUser findByNo(String no) {
        Example example = new Example(TesUser.class);
        example.createCriteria().andEqualTo("no", no);
        return tesUserMapper.selectByExample(example).get(0);
    }

    /**
     * 批量导入用户
     * @param data
     */
    @Override
    public void add(List<TesUser> data) {
        for (TesUser user : data) {
            String encodePassword = passwordEncoder.encode("123456");
            user.setPassword(encodePassword);
            int count = tesUserMapper.insertSelective(user);
        }
    }

    /*
    * 查询学生人数
    * */
    public int countStudent(){
        Example example = new Example(TesUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",4L);
        int count = tesUserMapper.selectCountByExample(example);
        return count;
    }

    /*
    * 查询教师人数
    * */
    public int countTeacher(){
        Example example = new Example(TesUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",3L);
        int count = tesUserMapper.selectCountByExample(example);
        return count;
    }

    /*
     * 查询班级数
     * */
    public int countClass(){
        TesClass tesClass = new TesClass();
        int count = tesClassMapper.selectCount(tesClass);
        return count;
    }

    /*
     * 查询学院数
     * */
    public int countDepartment(){
        TesDepartment tesDepartment = new TesDepartment();
        int count = tesDepartmentMapper.selectCount(tesDepartment);
        return count;
    }

}
