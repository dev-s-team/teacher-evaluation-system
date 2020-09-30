package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.dao.TesCourseMapper;
import com.csmaxwell.tes.dao.TesUserCourseMapper;
import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.domain.TesUserCourse;
import com.csmaxwell.tes.service.TesCourseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/15.
 */
@Service
public class TesCourseServiceImpl implements TesCourseService {

    @Autowired
    private TesCourseMapper tesCourseMapper;
    @Autowired
    private TesUserCourseMapper tesUserCourseMapper;
    @Autowired
    private TesUserMapper tesUserMapper;

    @Override
    public List<TesCourse> listAllCourse(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TesCourse.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andEqualTo("num", keyword);
            example.or(example.createCriteria().andLike("name", "%" + keyword + "%"));
        }
        return tesCourseMapper.selectByExample(example);
    }

    @Override
    public int create(TesCourse tesCourseParam) {
        int count = tesCourseMapper.insertSelective(tesCourseParam);
        return count;
    }

    @Override
    public TesCourse findById(Long id) {
        TesCourse tesCourse = new TesCourse();
        tesCourse.setId(id);
        return tesCourseMapper.selectOne(tesCourse);
    }

    @Override
    public int update(Long id, TesCourse tesCourseDto) {
        tesCourseDto.setId(id);
        return tesCourseMapper.updateByPrimaryKeySelective(tesCourseDto);
    }

    @Override
    public int delete(Long id) {
        TesCourse tesCourse = new TesCourse();
        tesCourse.setId(id);
        return tesCourseMapper.deleteByPrimaryKey(tesCourse);
    }

    @Override
    public TesUser findUserInfoById(String num) {
        // 通过课程id，查询用户课程中间表
        Example example1 = new Example(TesUserCourse.class);
        example1.createCriteria().andEqualTo("courseNum", num);
        List<TesUserCourse> userCourseList = tesUserCourseMapper.selectByExample(example1);

        TesUser tesUser = null;

        // 通过用户课程列表，查询角色为3(老师)
        for (TesUserCourse userCourse : userCourseList) {
            Example example2 = new Example(TesUser.class);
            example2.createCriteria().andEqualTo("no", userCourse.getUserNo());
            example2.createCriteria().andEqualTo("roleId", 3L);
            List<TesUser> userList = tesUserMapper.selectByExample(example2);
            if (userList.size() != 0) {
                tesUser = userList.get(0);
            }
        }

        return tesUser;
    }
}
