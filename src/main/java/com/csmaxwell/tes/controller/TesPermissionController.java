package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理Controller
 * Created by maxwell on 2020/9/26.
 */
@Api(tags = "TesPermissionController", description = "权限管理")
@RestController
@RequestMapping("/permission")
public class TesPermissionController {

    @Autowired
    private TesPermissionService tesPermissionService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(TesPermissionController.class);

    @ApiOperation(value = "获取权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesPermission>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesPermission> permissionList = tesPermissionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(permissionList));
    }

    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<TesPermission> addPermission(@RequestBody TesPermission tesPermissionParam, BindingResult result) {
        TesPermission tesPermission = tesPermissionService.addPermission(tesPermissionParam);
        if (tesPermission == null) {
            CommonResult.failed();
        }
        return CommonResult.success(tesPermission);
    }

    @ApiOperation(value = "删除权限")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:delete')")
    public CommonResult delete(@PathVariable long id) {
        int count = tesPermissionService.delete(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除权限失败");
        }
    }

    @ApiOperation(value = "根据id更新权限状态")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:update')")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody TesPermission tesPermission) {
        int count = tesPermissionService.update(id,tesPermission);
        if (count == 1) {
            return CommonResult.success("更新权限状态成功");
        } else {
            return CommonResult.failed("更新权限状态失败");
        }
    }


}
