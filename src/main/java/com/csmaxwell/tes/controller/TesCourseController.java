package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.service.TesCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("获取所有课程列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:course:read')")
    public CommonResult<List<TesCourse>> getCourseList() {
        return CommonResult.success(tesCourseService.listAllCourse());
    }

    @ApiOperation("根据id查询课程信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:course:read')")
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
    @PreAuthorize("hasAuthority('pms:course:create')")
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
    @PreAuthorize("hasAuthority('pms:course:update')")
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
    @PreAuthorize("hasAuthority('pms:course:delete')")
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
}
