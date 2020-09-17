package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.service.TesRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 * Created by yl on 2020/9/16.
 */
@Api(tags = "TesRoleController", description = "角色管理")
@RestController
@RequestMapping("/role")
public class TesRoleController {
    @Autowired
    private TesRoleService tesRoleService;

    @ApiOperation(value = "角色添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody TesRole tesRoleParam) {
        CommonResult commonResult;
        int count = tesRoleService.create(tesRoleParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("获取所有角色信息")
    @RequestMapping(value = "/findAll", method =  RequestMethod.GET)
    public List<TesRole> findAll() {
        return tesRoleService.selectAll();
    }

    @ApiOperation("删除角色信息")
    @RequestMapping(value = "/deleteByid/{roleId}", method =  RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long roleId) {
        CommonResult commonResult;
        int count=tesRoleService.delete(roleId);
        if (count == 1){
            commonResult = CommonResult.success(null);
        }else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "修改角色信息")
    @RequestMapping(value = "/update/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("roleId") Long roleId, @RequestBody TesRole tesRoleDto) {
        CommonResult commonResult;
        int count = tesRoleService.update(roleId, tesRoleDto);
        if (count == 1) {
            commonResult = CommonResult.success(tesRoleDto);
        } else {
            commonResult = CommonResult.failed("修改角色信息失败");
        }
        return commonResult;
    }

}
