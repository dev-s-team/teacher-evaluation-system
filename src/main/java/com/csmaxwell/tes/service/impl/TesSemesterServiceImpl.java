package com.csmaxwell.tes.service.impl;


import com.csmaxwell.tes.common.util.PageUtil;
import com.csmaxwell.tes.dao.TesEvaluationControlMapper;
import com.csmaxwell.tes.dao.TesEvaluationMapper;
import com.csmaxwell.tes.dao.TesSemesterMapper;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesSemester;
import com.csmaxwell.tes.dto.TesSemesterDto;
import com.csmaxwell.tes.service.TesSemesterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesSemesterServiceImpl implements TesSemesterService {


    @Autowired
    private TesSemesterMapper tesSemesterMapper;
    @Autowired
    private TesEvaluationControlMapper tesEvaluationControlMapper;
    @Autowired
    private TesEvaluationMapper tesEvaluationMapper;

    @Override
    public int create(String name, Integer status, List<String> studentIds,
                      List<String> teacherIds, List<String> leaderIds) {
        System.out.println("学生id: " + studentIds);
        System.out.println("教师id: " + teacherIds);
        System.out.println("领导id: " + leaderIds);
        System.out.println("学年名: " + name);
        System.out.println("状态: " + status);
        TesSemester tesSemester = new TesSemester();
        tesSemester.setName(name);
        int count = tesSemesterMapper.insertSelective(tesSemester);

        TesEvaluationControl evalControl = new TesEvaluationControl();
        System.out.println("学年id: " + tesSemester.getId());
        evalControl.setSemesterId(tesSemester.getId());
        evalControl.setStatus(status);
        tesEvaluationControlMapper.insertSelective(evalControl);

        // 添加学生、教师、领导评教表
        for (String studentId : studentIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControl.getId());
            e1.setIndicatorId(Long.valueOf(studentId));
            e1.setRoleId(4L);

            tesEvaluationMapper.insertSelective(e1);
        }

        for (String teacherId : teacherIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControl.getId());
            e1.setIndicatorId(Long.valueOf(teacherId));
            e1.setRoleId(3L);

            tesEvaluationMapper.insertSelective(e1);
        }

        for (String leaderId : leaderIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControl.getId());
            e1.setIndicatorId(Long.valueOf(leaderId));
            e1.setRoleId(2L);

            tesEvaluationMapper.insertSelective(e1);
        }

        return count;
    }

    @Override
    public int updateEval(List<String> studentIds, List<String> teacherIds,
                          List<String> leaderIds, String name, Integer semesterId) {
        // 1.根据semesterId更新学期
        TesSemester tesSemester = new TesSemester();
        tesSemester.setName(name);
        tesSemester.setId(Long.valueOf(semesterId));
        int count = tesSemesterMapper.updateByPrimaryKeySelective(tesSemester);

        // 2.根据学期id获取评教控制表id
        Example example = new Example(TesEvaluationControl.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("semesterId", semesterId);
        List<TesEvaluationControl> evalControlList =
                tesEvaluationControlMapper.selectByExample(example);

        // 3.删除评教控制表id相同的评教表数据
        Example example1 = new Example(TesEvaluation.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("evalCnotrolId", evalControlList.get(0).getId());
        int count1 = tesEvaluationMapper.deleteByExample(example1);

        // 4.根据传过来的数组，添加数据
        // 添加学生、教师、领导评教表
        for (String studentId : studentIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControlList.get(0).getId());
            e1.setIndicatorId(Long.valueOf(studentId));
            e1.setRoleId(4L);

            tesEvaluationMapper.insertSelective(e1);
        }

        for (String teacherId : teacherIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControlList.get(0).getId());
            e1.setIndicatorId(Long.valueOf(teacherId));
            e1.setRoleId(3L);

            tesEvaluationMapper.insertSelective(e1);
        }

        for (String leaderId : leaderIds) {
            TesEvaluation e1 = new TesEvaluation();
            e1.setEvalCnotrolId(evalControlList.get(0).getId());
            e1.setIndicatorId(Long.valueOf(leaderId));
            e1.setRoleId(2L);

            tesEvaluationMapper.insertSelective(e1);
        }

        return 0;
    }

    @Override
    public int deleteEval(List<String> studentIds, List<String> teacherIds,
                          List<String> leaderIds, Integer semesterId) {
        // 1.根据学期id获取评教控制表id
        Example example = new Example(TesEvaluationControl.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("semesterId", semesterId);
        List<TesEvaluationControl> evalControlList =
                tesEvaluationControlMapper.selectByExample(example);

        // 2.删除评教控制表id相同的评教表数据
        Example example1 = new Example(TesEvaluation.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("evalCnotrolId", evalControlList.get(0).getId());
        int count1 = tesEvaluationMapper.deleteByExample(example1);

        // 3.删除评教控制表记录
        tesEvaluationControlMapper.deleteByPrimaryKey(evalControlList.get(0).getId());

        // 4.删除学年
        tesSemesterMapper.deleteByPrimaryKey(semesterId);

        return count1;
    }

    @Override
    public TesSemester select(Long semesterId) {
        TesSemester tesSemester = tesSemesterMapper.selectByPrimaryKey(semesterId);
        return tesSemester;
    }

    @Override
    public int update(Long semesterId, TesSemester tesSemester) {
        tesSemester.setId(semesterId);
        int count = tesSemesterMapper.updateByPrimaryKeySelective(tesSemester);
        return count;
    }

    @Override
    public PageInfo<TesSemesterDto> list(Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum, pageSize);

        List<TesSemester> semesterList = tesSemesterMapper.selectAll();

        // 3. 封装list到PageInfo对象中自动分页
        PageInfo<TesSemester> semesterPageInfo = new PageInfo<>(semesterList);

        // 4. 转换为UserVo类型的PageInfo对象
        PageInfo<TesSemesterDto> semesterDtoPageInfo =
                PageUtil.PageInfo2PageInfoVo(semesterPageInfo);

        TesSemesterDto semesterDto = new TesSemesterDto();

        List<TesSemesterDto> semesterDtoList = new ArrayList<>();

        for (TesSemester tesSemester : semesterList) {
            Example example = new Example(TesEvaluationControl.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("semesterId", tesSemester.getId());
            List<TesEvaluationControl> evalControlList =
                    tesEvaluationControlMapper.selectByExample(example);

            semesterDto = new TesSemesterDto();
            BeanUtils.copyProperties(tesSemester, semesterDto);

            if (evalControlList.get(0) == null) {
                semesterDto.setStatus(0);
            } else {
                semesterDto.setStatus(evalControlList.get(0).getStatus());
            }
            semesterDtoList.add(semesterDto);
        }

        semesterDtoPageInfo.setList(semesterDtoList);

        // System.out.println(semesterDtoPageInfo);

        return semesterDtoPageInfo;
    }

    @Override
    public int delete(Long id) {
        int count = tesSemesterMapper.deleteByPrimaryKey(id);

        Example example = new Example(TesEvaluationControl.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("semesterId", id);
        tesEvaluationControlMapper.deleteByExample(example);

        return count;
    }


}
