package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.dto.TesUserLoginParam;
import com.csmaxwell.tes.service.TesUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * Created by maxwell on 2020/9/14.
 */
@Api(tags = "TesUserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class TesUserController {

    @Autowired
    private TesUserService tesUserService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<TesUser> register(@RequestBody TesUser tesUserParam, BindingResult result) {
        TesUser tesUser = tesUserService.register(tesUserParam);
        if (tesUser == null) {
            CommonResult.failed();
        }
        return CommonResult.success(tesUser);
    }

    @ApiOperation(value = "登录后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody TesUserLoginParam tesUserLoginParam, BindingResult result) {
        System.out.println("/user/login 登录");
        String token = tesUserService.login(tesUserLoginParam.getUsername(), tesUserLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户所有权限")
    @RequestMapping(value = "/permission/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesPermission>> getPermissionList(@PathVariable Long userId) {
        List<TesPermission> permissionList = tesUserService.getPermissionList(userId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult logout() {

        System.out.println("/user/logout 退出登录");

        return null;
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:create')")
    public CommonResult create(@RequestBody TesUser tesUserParam) {
        CommonResult commonResult;
        int count = tesUserService.create(tesUserParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:course:delete')")
    public CommonResult delete(@PathVariable Long userId) {
        int count = tesUserService.delete(userId);
        if (count ==1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除用户失败");
        }
    }

    @ApiOperation(value = "查询用户信息")
    @RequestMapping(value = "/reade/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:reade')")
    public CommonResult reade(@PathVariable Long userId) {
        TesUser tesUser = tesUserService.select(userId);
        if (tesUser != null) {
            return CommonResult.success(tesUser);
        } else {
            return CommonResult.failed("查询用户信息失败");
        }
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "/update/{userId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:update')")
    public CommonResult update(@PathVariable Long userId ,@RequestBody TesUser tesUser) {
        int count = tesUserService.update(userId,tesUser);
        if (count == 1) {
            return CommonResult.success("更新用户信息成功");
        } else {
            return CommonResult.failed("更新用户信息失败");
        }
    }
}
