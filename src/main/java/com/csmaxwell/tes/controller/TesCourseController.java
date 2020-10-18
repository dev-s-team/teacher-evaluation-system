package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.dto.TesUserEvalDto;
import com.csmaxwell.tes.service.TesCourseService;
import com.csmaxwell.tes.service.TesUserService;
import com.csmaxwell.tes.service.TesEvaluationResultService;
import com.csmaxwell.tes.vo.CourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * S
 * Created by maxwell on 2020/9/15.
 */
@RestController
@Api(tags = "TesCourseController", description = "课程管理")
@RequestMapping("/course")
public class TesCourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesCourseController.class);

    @Autowired
    private TesCourseService tesCourseService;

    @Autowired
    private TesUserService tesUserService;

    @Autowired
    private TesEvaluationResultService tesEvaluationResultService;

    @ApiOperation("获取所有课程列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:read')")
    public CommonResult<CommonPage<TesCourse>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesCourse> courseList = tesCourseService.listAllCourse(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(courseList));
    }

    @ApiOperation("通过用户no获取课程列表")
    @RequestMapping(value = "listByNo/{no}", method = RequestMethod.GET)
    @ResponseBody
//    @PreAuthorize("hasAuthority('lms:course:read')")
    public CommonResult<List<TesCourse>>listByNo(@PathVariable("no") String no) {
        // 获取评教对象
        TesUser tesUser = tesUserService.findByNo(no);
        Long id = tesUser.getId();
        // 查询用户有哪些课程
        List<TesCourse> courseList = tesUserService.findCourseListById(id);

        if (courseList != null) {
            return CommonResult.success(courseList);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据id查询课程信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:read')")
    public CommonResult<TesCourse> findCourseById(@PathVariable("id") Long id) {
        TesCourse tesCourse = tesCourseService.findById(id);
        if (tesCourse != null) {
            return CommonResult.success(tesCourse);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("创建课程")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:create')")
    public CommonResult create(@RequestBody TesCourse tesCourseParam) {
        CommonResult commonResult;
        int count = tesCourseService.create(tesCourseParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("根据id更新课程信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:update')")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody TesCourse tesCourseDto, BindingResult result) {
        CommonResult commonResult;
        int count = tesCourseService.update(id, tesCourseDto);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("updateCourse success: {}", tesCourseDto);
        } else {
            commonResult = CommonResult.failed("更新课程信息失败");
            LOGGER.debug("updateCourse failed: {}", tesCourseDto);
        }
        return commonResult;
    }

    @ApiOperation("根据id删除课程")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:delete')")
    public CommonResult delete(@PathVariable("id") Long id) {
        CommonResult commonResult;
        int count = tesCourseService.delete(id);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteCourse success: id = {}", id);
        } else {
            commonResult = CommonResult.failed("删除课程信息失败");
            LOGGER.debug("deleteCourse failed: id = {}", id);
        }
        return commonResult;
    }

    @ApiOperation("根据课程id返回封装图表数据的实体类")
    @RequestMapping(value = "/getCount/{id}", method = RequestMethod.GET)
    @ResponseBody
//    @PreAuthorize("hasAuthority('lms:course:read')")
    public CommonResult getCount(@PathVariable("id") Long id) {
        TesCourse tesCourse = tesCourseService.findById(id);
        int courseUserCount1 =tesCourseService.getCount(tesCourse.getNum());
        int evaluatedCount1 =tesEvaluationResultService.evaluatedCount(id);
        int noEvaluationCount1 = courseUserCount1 - evaluatedCount1;
        String evaluatedCount = String.valueOf(evaluatedCount1);
        String noEvaluationCount = String.valueOf(noEvaluationCount1);
        Map<String,String> map1=new HashMap<>();
        map1.put("人数", "已评教人数");
        map1.put("访问用户", evaluatedCount);
        Map<String,String> map2=new HashMap<>();
        map2.put("人数", "未评教人数");
        map2.put("访问用户", noEvaluationCount);
        CourseVo courseVo = new CourseVo();
        String[] columns = new String[]{"人数","访问用户"};
        courseVo.setColumns(columns);
        List<Map<String,String>> rows = new ArrayList<Map<String, String>>();
        rows.add(map1);
        rows.add(map2);
        courseVo.setRows(rows);
        if (courseVo != null) {
            return CommonResult.success(courseVo);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据课程id查询该课程的用户人数")
    @RequestMapping(value = "/getAllCount/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:course:read')")
    public CommonResult getAlCount(@PathVariable("id") Long id) {
        TesCourse tesCourse = tesCourseService.findById(id);
        int courseUserCount =tesCourseService.getCount(tesCourse.getNum());

        if (courseUserCount >=0) {
            return CommonResult.success(courseUserCount);
        } else {
            return CommonResult.failed();
        }
    }


}
