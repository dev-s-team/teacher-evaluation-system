package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 指标管理
 */

@Api(tags = "TesIndicatorController", description = "指标管理")
@RestController
@RequestMapping("/indicator")
public class TesIndicatorController {

    @Autowired
    private TesIndicatorService tesIndicatorService;

    @ApiOperation(value = "新增指标")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:indicator:create')")
    public CommonResult create(@RequestBody TesIndicator tesindicatorParam) {
        CommonResult commonResult;
        int count = tesIndicatorService.create(tesindicatorParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除指标")
    @RequestMapping(value = "/delete/{indicatorId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:indicator:delete')")
    public CommonResult delete(@PathVariable Long indicatorId) {
        int count = tesIndicatorService.delete(indicatorId);
        if (count ==1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除指标失败");
        }
    }

    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesIndicator>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesIndicator> list = tesIndicatorService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "查询指标信息")
    @RequestMapping(value = "/reade/{indicatorId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:indicator:read')")
    public CommonResult reade(@RequestParam Long indicatorId) {
        TesIndicator tesindicator = tesIndicatorService.select(indicatorId);
        if (tesindicator != null) {
            return CommonResult.success(tesindicator);
        } else {
            return CommonResult.failed("查询指标信息失败");
        }
    }

    @ApiOperation(value = "更新指标信息")
    @RequestMapping(value = "/update/{indicatorId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:indicator:update')")
    public CommonResult update(@PathVariable Long indicatorId ,@RequestBody TesIndicator tesindicator) {
        int count = tesIndicatorService.update(indicatorId,tesindicator);
        if (count == 1) {
            return CommonResult.success("更新指标信息成功");
        } else {
            return CommonResult.failed("更新指标信息失败");
        }
    }

    @ApiOperation(value = "更新指标状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        int count = tesIndicatorService.updateStatus(id, status);
        if (count == 1) {
            return CommonResult.success("更新指标信息成功");
        } else {
            return CommonResult.failed("更新指标信息失败");
        }
    }

    @ApiOperation("获取所有指标")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesIndicator>> all() {
        List<TesIndicator> indicatorList = tesIndicatorService.all();
        return CommonResult.success(indicatorList);
    }
}

