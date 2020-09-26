package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesClass;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesClassService;
import com.csmaxwell.tes.service.TesRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/17.
 */
@Api(tags = "TesClassController", description = "班级管理")
@RestController
@RequestMapping("/class")
public class TesClassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesClassController.class);

    @Autowired
    private TesClassService tesClassService;

    @ApiOperation(value = "新增班级")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:class:create')")
    public CommonResult create(@RequestBody TesClass tesClassParam) {
        CommonResult commonResult;
        int count = tesClassService.create(tesClassParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除班级")
    @RequestMapping(value = "/delete/{classId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:class:delete')")
    public CommonResult delete(@PathVariable Long classId) {
        int count = tesClassService.delete(classId);
        if (count ==1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除班级失败");
        }
    }

    @ApiOperation(value = "查询班级列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesClass>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesClass> classList = tesClassService.findAll(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(classList));
    }

    @ApiOperation(value = "查询班级信息")
    @RequestMapping(value = "/reade/{classId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:class:reade')")
    public CommonResult reade(@PathVariable Long classId) {
        TesClass tesClass = tesClassService.select(classId);
        if (tesClass != null) {
            return CommonResult.success(tesClass);
        } else {
            return CommonResult.failed("查询班级信息失败");
        }
    }

    @ApiOperation(value = "更新班级信息")
    @RequestMapping(value = "/update/{classId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:class:update')")
    public CommonResult update(@PathVariable Long classId ,@RequestBody TesClass tesClass) {
        int count = tesClassService.update(classId,tesClass);
        if (count == 1) {
            return CommonResult.success("更新班级信息成功");
        } else {
            return CommonResult.failed("更新班级信息失败");
        }
    }
}
