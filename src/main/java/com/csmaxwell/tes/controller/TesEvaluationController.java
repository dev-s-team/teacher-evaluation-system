package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.common.constant.Status;
import com.csmaxwell.tes.common.constant.UserRole;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.dto.TesUserEvalDto;
import com.csmaxwell.tes.service.*;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesEvaluationControlService;
import com.csmaxwell.tes.service.TesEvaluationService;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TesSemesterService tesSemesterService;
    @Autowired
    private TesEvaluationResultService tesEvaluationResultService;

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
    public CommonResult<List<TesIndicator>> list(@RequestParam(value = "userId",
            required = false) Long userId,
                                                 @RequestParam(value = "roleId",
                                                         required = false) Long roleId,
                                                 @RequestParam(value = "targetId", required =
                                                         false) Long targetId,
                                                 @RequestParam(value = "courseId", required =
                                                         false) Long courseId,
                                                 @RequestParam(value = "semesterId", required =
                                                         false) Long semesterId) {
        System.out.println("学期id: " + semesterId);
        System.out.println("用户id: " + userId);
        List<TesEvaluationControl> evlControlList = tesEvaluationControlService.tecList(semesterId);

        TesEvaluationControl evalControl = evlControlList.get(0);
        System.out.println(evalControl);

        List<TesEvaluation> evaluationList = tesEvaluationService.teList(evalControl.getId(),
                roleId);
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

        // 获取评教结果列表
        List<TesEvaluationResult> evalResultList = tesEvaluationResultService.all();

        // 查询用户有哪些课程
        List<TesCourse> courseList = tesUserService.findCourseListById(id);

        // 根据课程查询
        for (TesCourse course : courseList) {
            TesUserEvalDto userEvalDto = new TesUserEvalDto();



            // 根据课程获取学期id
            Long semesterId = course.getSemesterId();
            // 获取学期信息
            TesSemester tesSemester = tesSemesterService.select(semesterId);

            // 根据学期查询批次是否开启
            final TesEvaluationControl evalControl =
                    tesEvaluationControlService.tecList(semesterId).get(0);

            if (Status.ENABLED.equals(evalControl.getStatus())) {
                // 查询评教目标信息
                TesUser targetUser = tesCourseService.findUserInfoById(course.getNum());

                // 判断是否评教过
                for (TesEvaluationResult result : evalResultList) {
                    if (result.getCourseId().equals(course.getId()) &&
                            result.getUserId().equals(id) &&
                            result.getRoleId().equals(tesRole.getId()) &&
                            result.getTargetId() == targetUser.getId()) {
                        userEvalDto.setEval(true);
                        break;
                    } else {
                        userEvalDto.setEval(false);
                    }
                }

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

    @ApiOperation("改变评教控制状态")
    @RequestMapping(value = "/control/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable("id") Long semesterId,
                                     @RequestParam("status") Integer status) {
        int count = tesEvaluationControlService.updateStatus(semesterId, status);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取评教指标")
    @RequestMapping(value = "/evalItem/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult evalItem(@PathVariable("id") Long semesterId) {
        List<TesEvaluationControl> evalControlList =
                tesEvaluationControlService.tecList(semesterId);
        Map<String, List> map = new HashMap<>();

        // 学生
        List<Integer> studentList =
                tesEvaluationService.findBySmsIdAndRoleId(evalControlList.get(0).getId(), 4L);
        // 教师
        List<Integer> teacherList =
                tesEvaluationService.findBySmsIdAndRoleId(evalControlList.get(0).getId(), 3L);
        // 领导
        List<Integer> leaderList =
                tesEvaluationService.findBySmsIdAndRoleId(evalControlList.get(0).getId(), 2L);

        System.out.println(studentList);
        System.out.println(teacherList);
        System.out.println(leaderList);

        map.put("student", studentList);
        map.put("teacher", teacherList);
        map.put("leader", leaderList);


        return CommonResult.success(map);
    }

    @ApiOperation(value = "同行评教")
    @RequestMapping(value = "/otherCourseList/{no}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesUserEvalDto>> otherCourseList(@PathVariable("no") String no) {

        // 获取评教对象
        TesUser tesUser1 = tesUserService.findByNo(no);
        Long id = tesUser1.getId();
        // 获取评教人角色
        TesRole tesRole = tesUserService.findRoleById(id);
        // 获取班级信息
        TesClass tesClass = tesUserService.findClassById(id);
        // 获取院系信息
        TesDepartment tesDept = tesUserService.findDeptById(id);
        // 查询老师列表
        List<TesUser> tesUsers = tesUserService.byRoleId();
        // TesUserCourse tesUserCourse = new TesUserCourse();
        // TesCourse tesCourse = new TesCourse();
        List<TesCourse> tesCourses = new ArrayList<>();
        List<TesUserEvalDto> userEvalDtoList = new ArrayList<>();

        // 获取评教结果列表
        List<TesEvaluationResult> evalResultList = tesEvaluationResultService.all();

        for (TesUser tesUser : tesUsers) {
            if (!no.equals(tesUser.getNo())) {
                List<TesUserCourse> tesUserCourses = tesUserService.findByUserNo(tesUser.getNo());
                for (TesUserCourse tesUserCourse : tesUserCourses) {
                    System.out.println(tesUserCourse.getCourseNum() + "CourseNum");
                    TesCourse tesCourse = tesCourseService.findByNum(tesUserCourse.getCourseNum());
                    tesCourses.add(tesCourse);
                    System.out.println(tesCourse + "tesCourse");
                }
                System.out.println(tesCourses + "tesCourses");


            }


        }
        for (TesCourse tesCourse1 : tesCourses) {
            // 根据课程查询
            TesUserEvalDto userEvalDto = new TesUserEvalDto();
            // 根据课程获取学期id
            Long semesterId = tesCourse1.getSemesterId();
            // 获取学期信息
            TesSemester tesSemester = tesSemesterService.select(semesterId);

            System.out.println(tesCourse1.getName());

            // 根据学期查询批次是否开启
            final TesEvaluationControl evalControl =
                    tesEvaluationControlService.tecList(semesterId).get(0);

            if (Status.ENABLED.equals(evalControl.getStatus())) {
                // 查询评教目标信息
                TesUser targetUser = tesCourseService.findUserInfoById(tesCourse1.getNum());

                // 判断是否评教过
                for (TesEvaluationResult result : evalResultList) {
                    if (result.getCourseId().equals(tesCourse1.getId()) &&
                            result.getUserId().equals(id) &&
                            result.getRoleId().equals(tesRole.getId()) &&
                            result.getTargetId() == targetUser.getId()) {
                        userEvalDto.setEval(true);
                        break;
                    } else {
                        userEvalDto.setEval(false);
                    }
                }

                userEvalDto.setUserId(id);
                userEvalDto.setRoleId(tesRole.getId());
                userEvalDto.setTargetId(targetUser.getId());
                userEvalDto.setTargetName(targetUser.getUsername());
                userEvalDto.setCourseId(tesCourse1.getId());
                userEvalDto.setCourseName(tesCourse1.getName());
                userEvalDto.setClassId(tesClass.getId());
                userEvalDto.setClassNo(tesClass.getNo());
                userEvalDto.setDeptId(tesDept.getId());
                userEvalDto.setDeptName(tesDept.getName());
                userEvalDto.setSemesterId(tesSemester.getId());
                userEvalDto.setSemesterName(tesSemester.getName());
                userEvalDtoList.add(userEvalDto);
            }
        }
        return CommonResult.success(userEvalDtoList);
    }

    @ApiOperation("领导评教的列表")
    @RequestMapping(value = "/leaderCourseList/{no}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult leaderCourseList(@PathVariable("no") String no) {

        List<TesUserEvalDto> userEvalDtoList = new ArrayList<>();
        // 获取评教对象
        TesUser tesUser = tesUserService.findByNo(no);
        Long id = tesUser.getId();
        // 获取评教人角色
        TesRole tesRole = tesUserService.findRoleById(id);
        // 获取院系信息
        TesDepartment tesDept = tesUserService.findDeptById(id);

        // 获取评教结果列表
        List<TesEvaluationResult> evalResultList = tesEvaluationResultService.all();

        // 查询出本院系老师的信息
        List<TesUser> teacherList = tesUserService.findUserByDeptNo(tesDept.getNo(),
                UserRole.TEACHER);
        for (TesUser user : teacherList) {
            System.out.println(user.getUsername());
        }

        List<TesCourse> courseList = new ArrayList<>();

        // 根据教师列表获取教的课程列表
        for (TesUser teacher : teacherList) {
            // 查询用户有哪些课程
            List<TesCourse> list = tesUserService.findCourseListById(teacher.getId());
            for (TesCourse course : list) {
                courseList.add(course);
            }
        }

        for (TesCourse tesCourse : courseList) {
            System.out.println(tesCourse.getName());
        }

        if (courseList != null) {
            // 根据课程查询
            for (TesCourse course : courseList) {
                TesUserEvalDto userEvalDto = new TesUserEvalDto();

                // 根据课程获取学期id
                Long semesterId = course.getSemesterId();
                // 获取学期信息
                TesSemester tesSemester = tesSemesterService.select(semesterId);

                // 根据学期查询批次是否开启
                final TesEvaluationControl evalControl =
                        tesEvaluationControlService.tecList(semesterId).get(0);

                System.out.println(course.getName());

                if (Status.ENABLED.equals(evalControl.getStatus())) {
                    // 查询评教目标信息
                    TesUser targetUser = tesCourseService.findUserInfoById(course.getNum());

                    // 判断是否评教过
                    for (TesEvaluationResult result : evalResultList) {
                        if (result.getCourseId().equals(course.getId()) &&
                                result.getUserId().equals(id) &&
                                result.getRoleId().equals(tesRole.getId()) &&
                                result.getTargetId().equals(targetUser.getId())) {
                            userEvalDto.setEval(true);
                            break;
                        } else {
                            userEvalDto.setEval(false);
                        }
                    }

                    userEvalDto.setUserId(id);
                    userEvalDto.setRoleId(tesRole.getId());
                    userEvalDto.setTargetId(targetUser.getId());
                    userEvalDto.setTargetName(targetUser.getUsername());
                    userEvalDto.setCourseId(course.getId());
                    userEvalDto.setCourseName(course.getName());
                    userEvalDto.setDeptId(tesDept.getId());
                    userEvalDto.setDeptName(tesDept.getName());
                    userEvalDto.setSemesterId(tesSemester.getId());
                    userEvalDto.setSemesterName(tesSemester.getName());
                    userEvalDtoList.add(userEvalDto);
                }
            }
        }

        return CommonResult.success(userEvalDtoList);
    }



}
