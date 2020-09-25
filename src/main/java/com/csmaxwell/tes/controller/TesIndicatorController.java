package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/delete/{indicatorId}", method = RequestMethod.GET)
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

    @ApiOperation(value = "查询指标信息")
    @RequestMapping(value = "/reade/{indicatorId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:indicator:reade')")
    public CommonResult reade(@PathVariable Long indicatorId) {
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
}
