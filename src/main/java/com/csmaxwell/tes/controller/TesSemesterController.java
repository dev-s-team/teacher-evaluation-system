package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesSemester;
import com.csmaxwell.tes.dto.TesSemesterDto;
import com.csmaxwell.tes.service.TesSemesterService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "TesSemesterController", description = "批次管理")
@RestController
@RequestMapping("/semester")
public class TesSemesterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesSemesterController.class);

    @Autowired
    private TesSemesterService tesSemesterService;

    @ApiOperation(value = "新增批次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    // @PreAuthorize("hasAuthority('ems:batch:create')")
    public CommonResult create(@RequestParam("studentCheckIds") List<String> studentIds,
                               @RequestParam("teacherCheckIds") List<String> teacherIds,
                               @RequestParam("leaderCheckIds") List<String> leaderIds,
                               @RequestParam("name") String name,
                               @RequestParam("status") Integer status) {
        CommonResult commonResult;
        System.out.println("新增批次");

        int count = tesSemesterService.create(name, status, studentIds, teacherIds, leaderIds);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("更新批次、评教相关")
    @RequestMapping(value = "/updateEval", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateEval(@RequestParam("studentCheckIds") List<String> studentIds,
                                   @RequestParam("teacherCheckIds") List<String> teacherIds,
                                   @RequestParam("leaderCheckIds") List<String> leaderIds,
                                   @RequestParam("name") String name,
                                   @RequestParam("id") Integer semesterId) {
        tesSemesterService.updateEval(studentIds, teacherIds, leaderIds, name, semesterId);

        return CommonResult.success(null);
    }

    @ApiOperation("删除学年、批次、评教相关")
    @RequestMapping(value = "/deleteEval", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteEval(@RequestParam("studentCheckIds") List<String> studentIds,
                                   @RequestParam("teacherCheckIds") List<String> teacherIds,
                                   @RequestParam("leaderCheckIds") List<String> leaderIds,
                                   @RequestParam("id") Integer semesterId) {
        tesSemesterService.deleteEval(studentIds, teacherIds, leaderIds, semesterId);

        return CommonResult.success(null);
    }

    @ApiOperation(value = "查询批次信息")
    @RequestMapping(value = "/reade/{semesterId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('ems:batch:reade')")
    public CommonResult reade(@PathVariable Long semesterId) {
        TesSemester tesSemester = tesSemesterService.select(semesterId);
        if (tesSemester != null) {
            return CommonResult.success(tesSemester);
        } else {
            return CommonResult.failed("查询用户信息失败");
        }
    }

    @ApiOperation(value = "更新批次信息")
    @RequestMapping(value = "/update/{semesterId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('ems:batch:update')")
    public CommonResult update(@PathVariable Long semesterId,
                               @RequestBody TesSemester tesSemester) {
        int count = tesSemesterService.update(semesterId, tesSemester);
        if (count == 1) {
            return CommonResult.success("更新用户信息成功");
        } else {
            return CommonResult.failed("更新用户信息失败");
        }
    }

    @ApiOperation("删除批次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = tesSemesterService.delete(id);
        if (count == 1) {
            return CommonResult.success("更新用户信息成功");
        } else {
            return CommonResult.failed("更新用户信息失败");
        }
    }

    @ApiOperation("获取学期列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<TesSemesterDto>> list(@RequestParam(value = "pageSize",
            defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum",
                                                               defaultValue = "1") Integer pageNum) {
        PageInfo<TesSemesterDto> semesterList = tesSemesterService.list(pageSize, pageNum);
        return CommonResult.success(semesterList);
    }

}
