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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/15.
 */
@Controller
@Api(tags = "TesCourseController", description = "课程管理")
@RequestMapping("/course")
public class TesCourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesCourseController.class);

    @Autowired
    private TesCourseService tesCourseService;

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:course:read')")
    public CommonResult<List<TesCourse>> getCourseList() {
        return CommonResult.success(tesCourseService.listAllCourse());
    }
}
