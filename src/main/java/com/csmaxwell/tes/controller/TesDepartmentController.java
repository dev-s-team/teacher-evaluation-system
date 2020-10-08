package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesDepartment;
import com.csmaxwell.tes.service.TesDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/17.
 */
@Api(tags = "TesDepartmentController", description = "院系管理")
@RestController
@RequestMapping("/dept")
public class TesDepartmentController {
    @Autowired
    private TesDepartmentService tesDepartmentService;

    @ApiOperation(value = "院系添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody TesDepartment tesDepartmentParam) {
        CommonResult commonResult;
        int count = tesDepartmentService.create(tesDepartmentParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "院系查看")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesDepartment>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesDepartment> departmentList = tesDepartmentService.selectAll(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(departmentList));
    }

    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesDepartment>> all() {
        List<TesDepartment> deptList = tesDepartmentService.all();
        return CommonResult.success(deptList);
    }

    @ApiOperation(value = "院系删除")
    @RequestMapping(value = "/deleteByid/{departmentId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long departmentId){
        CommonResult commonResult;
        int count = tesDepartmentService.delete(departmentId);
        if (count == 1){
            commonResult=CommonResult.success(null);
        }else {
            commonResult=CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "院系修改")
    @RequestMapping(value = "/update/{departmentId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long departmentId,@RequestBody TesDepartment departmentDto){
        CommonResult commonResult;
        int count=tesDepartmentService.update(departmentId,departmentDto);
        if (count == 1){
            commonResult=CommonResult.success(null);
        }else {
            commonResult=CommonResult.failed();
        }
        return commonResult;
    }
}
