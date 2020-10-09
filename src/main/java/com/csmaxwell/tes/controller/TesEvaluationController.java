package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.common.constant.EvalOption;
import com.csmaxwell.tes.dao.TesCourseMapper;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.dto.TesUserEvalDto;
import com.csmaxwell.tes.service.*;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesEvaluationControlService;
import com.csmaxwell.tes.service.TesEvaluationService;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/27.
 */
@Api(tags = "TesEvaluationController", description = "评教管理")
@RestController
@RequestMapping("/eval")
public class TesEvaluationController {

    @Autowired
    private TesEvaluationService tesEvaluationService;

    @Autowired
    private TesEvaluationControlService tesEvaluationControlService;

    @Autowired
    private TesIndicatorService tesIndicatorService;
    @Autowired
    private TesClassService tesClassService;
    @Autowired
    private TesUserService tesUserService;
    @Autowired
    private TesCourseService tesCourseService;

    @ApiOperation(value = "发布评教")
    @RequestMapping(value = "/update/{evaluationControlId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:evaluationControl:update')")
    public CommonResult update(@PathVariable Long evaluationControlId) {

        CommonResult commonResult;

        int count = tesEvaluationService.updateById(evaluationControlId);

        if (count == 1) {
            commonResult = CommonResult.success("该评教发布成功！");
        } else {
            commonResult = CommonResult.failed("该评教已经发布了！");
        }
        return commonResult;
    }

    @ApiOperation(value = "开始评教")
    @RequestMapping(value = "/startEvaluation", method = RequestMethod.POST)
    @ResponseBody
    // @PreAuthorize("hasAuthority('pms:evaluation:startEvaluation')")
    public CommonResult<List<TesIndicator>> list(@RequestParam(value = "userId", required = false) Long userId,
                                                     @RequestParam(value = "roleId", required = false) Long roleId,
                                                     @RequestParam(value = "targetId", required = false) Long targetId,
                                                     @RequestParam(value = "courseId", required = false) Long courseId,
                                                     @RequestParam(value = "semesterId", required = false) Long semesterId) {
        System.out.println("学期id: " + semesterId);
        System.out.println("用户id: " + userId);
        List<TesEvaluationControl> evlControlList = tesEvaluationControlService.tecList(semesterId);

        TesEvaluationControl evalControl = evlControlList.get(0);
        System.out.println(evalControl);

        List<TesEvaluation> evaluationList = tesEvaluationService.teList(evalControl.getId());
        System.out.println(evaluationList);

        TesIndicator indicatorList = null;
        List<TesIndicator> allIndicatorLists = new ArrayList<TesIndicator>();


        for (TesEvaluation tesEvaluation : evaluationList) {

            indicatorList = tesIndicatorService.indicatorList(tesEvaluation.getIndicatorId());
            System.out.println(indicatorList);

            allIndicatorLists.add(indicatorList);
            System.out.println(allIndicatorLists);
        }
        return CommonResult.success(allIndicatorLists);
    }


    @ApiOperation(value = "查询评教结果")
    @RequestMapping(value = "/reade", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:evaluationResult:read')")
    public CommonResult reade() {
        List<TesEvaluation> tesEvaluations = tesEvaluationService.select();
        if (tesEvaluations != null) {

            return CommonResult.success(tesEvaluations);
        } else {
            return CommonResult.failed("查询用户信息失败");
        }
    }

    @ApiOperation(value = "根据用户编号查询评教课程")
    @RequestMapping(value = "/courseList/{no}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesUserEvalDto>> findCourse(@PathVariable("no") String no) {

        List<TesUserEvalDto> userEvalDtoList = new ArrayList<>();
        // 获取评教对象
        TesUser tesUser = tesUserService.findByNo(no);
        Long id = tesUser.getId();
        // 获取评教人角色
        TesRole tesRole = tesUserService.findRoleById(id);
        // 获取班级信息
        TesClass tesClass = tesUserService.findClassById(id);
        // 获取院系信息
        TesDepartment tesDept = tesUserService.findDeptById(id);
        // 获取学期信息
        TesSemester tesSemester = tesUserService.findSemesterById(id);

        // 查询用户有哪些课程
        List<TesCourse> courseList = tesUserService.findCourseListById(id);

        // 根据课程查询
        for (TesCourse course : courseList) {
            TesUserEvalDto userEvalDto = new TesUserEvalDto();

            System.out.println(course.getName());
            // 查询评教目标信息
            TesUser targetUser = tesCourseService.findUserInfoById(course.getNum());

            userEvalDto.setUserId(id);
            userEvalDto.setRoleId(tesRole.getId());
            userEvalDto.setTargetId(targetUser.getId());
            userEvalDto.setTargetName(targetUser.getUsername());
            userEvalDto.setCourseId(course.getId());
            userEvalDto.setCourseName(course.getName());
            userEvalDto.setClassId(tesClass.getId());
            userEvalDto.setClassNo(tesClass.getNo());
            userEvalDto.setDeptId(tesDept.getId());
            userEvalDto.setDeptName(tesDept.getName());
            userEvalDto.setSemesterId(tesSemester.getId());
            userEvalDto.setSemesterName(tesSemester.getName());
            userEvalDtoList.add(userEvalDto);
        }

        return CommonResult.success(userEvalDtoList);
    }

    @ApiOperation("评教控制列表")
    @RequestMapping(value = "/control/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesEvaluationControl>> controlList() {
        List<TesEvaluationControl> controlList = tesEvaluationControlService.list();
        return CommonResult.success(controlList);
    }

}
