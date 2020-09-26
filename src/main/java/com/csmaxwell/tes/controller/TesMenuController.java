package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.service.TesMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/26.
 */
@Api(tags = "TesMenuController", description = "菜单管理")
@RestController
@RequestMapping("/menu")
public class TesMenuController {

    @Autowired
    private TesMenuService tesMenuService;

    @ApiOperation("新增菜单项")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody TesMenu tesMenu) {
        CommonResult commonResult;
        int count = tesMenuService.create(tesMenu);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("删除菜单项")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        CommonResult commonResult;
        int count = tesMenuService.delete(id);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("更新菜单项")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id, @RequestBody TesMenu tesMenu) {
        CommonResult commonResult;
        int count = tesMenuService.update(id, tesMenu);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesMenu>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesMenu> list = tesMenuService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据id查询")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TesMenu> select(@PathVariable("id") Long id) {
        TesMenu tesMenu = tesMenuService.select(id);
        if (tesMenu != null) {
            return CommonResult.success(tesMenu);
        } else {
            return CommonResult.failed();
        }
    }


}
