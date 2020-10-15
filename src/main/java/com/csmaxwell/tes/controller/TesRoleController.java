package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.service.TesPermissionService;
import com.csmaxwell.tes.service.TesRoleService;
import com.csmaxwell.tes.service.TesMenuService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private TesMenuService tesMenuService;

    @Autowired
    private TesPermissionService tesPermissionService;

    @ApiOperation(value = "角色添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:role:create')")
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
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CommonResult<List<TesRole>> findAll() {

        return CommonResult.success(tesRoleService.selectAll());
    }

    @ApiOperation("删除角色信息")
    @RequestMapping(value = "/deleteByid/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:role:delete')")
    public CommonResult delete(@PathVariable Long roleId) {
        CommonResult commonResult;
        int count = tesRoleService.delete(roleId);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "修改角色信息")
    @RequestMapping(value = "/update/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:role:update')")
    public CommonResult update(@PathVariable("roleId") Long roleId,
                               @RequestBody TesRole tesRoleDto) {
        CommonResult commonResult;
        int count = tesRoleService.update(roleId, tesRoleDto);
        if (count == 1) {
            commonResult = CommonResult.success(tesRoleDto);
        } else {
            commonResult = CommonResult.failed("修改角色信息失败");
        }
        return commonResult;
    }

    @ApiOperation("切换显示状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestBody TesRole tesRole) {
        CommonResult commonResult;
        int count = tesRoleService.updateStatus(id, tesRole);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "分页查询角色信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesRole>> list(@RequestParam(value = "keyword", required =
            false) String keyword,
                                                  @RequestParam(value = "pageNum", defaultValue =
                                                          "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue
                                                          = "5") Integer pageSize) {
        List<TesRole> list = tesRoleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "查询角色对应菜单")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:menu:read')")
    public CommonResult<List<TesMenu>> listMenu(@PathVariable("roleId") Long roleId) {
        List<TesRoleMenu> tesRoleMenuses = tesRoleService.listRoleMenu(roleId);
        TesMenu tesMenu = null;
        List<TesMenu> allMenuLists = new ArrayList<TesMenu>();
        for (TesRoleMenu tesRoleMenu : tesRoleMenuses) {

            tesMenu = tesMenuService.select(tesRoleMenu.getMenuId());
            System.out.println(tesMenu);

            allMenuLists.add(tesMenu);
            System.out.println(allMenuLists);
        }
        return CommonResult.success(allMenuLists);
    }

    @ApiOperation(value = "分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @ResponseBody
//    @PreAuthorize("hasAuthority('ums:role:create')")
    public CommonResult allocMenu(@RequestParam("roleId") Long roleId,
                                  @RequestParam("menuIds") List<Long> menuIds) {
        CommonResult commonResult;
//        List<TesRoleMenu> tesRoleMenu = new ArrayList<TesRoleMenu>();
//        TesRoleMenu tesRoleMenu = new TesRoleMenu();
//        BeanUtils.copyProperties(tesRoleParam, tesRoleMenu);
        System.out.println(menuIds + "`111222333");
        int count = 0;
        try {
            int count1 = tesRoleService.delRoleMenu(roleId);
        } catch (Exception e) {
            System.out.println("删除菜单失败");
        }
        for (Long menuId : menuIds) {

            count = tesRoleService.insertMenu(roleId, menuId);
        }

        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "查询角色对应权限")
    @RequestMapping(value = "/listPermission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesPermission>> listPermission(@PathVariable("roleId") Long roleId) {
        List<TesRolePermission> tesRolePermissions = tesRoleService.listRolePermission(roleId);
        TesPermission tesPermission = null;
        List<TesPermission> allPermissionLists = new ArrayList<TesPermission>();
        for (TesRolePermission tesRolePermission : tesRolePermissions) {

            tesPermission = tesPermissionService.select(tesRolePermission.getPermissionId());
//            System.out.println(tesPermission);

            allPermissionLists.add(tesPermission);
//            System.out.println(allPermissionLists);
        }
        return CommonResult.success(allPermissionLists);
    }

    @ApiOperation(value = "分配权限")
    @RequestMapping(value = "/allocPermission", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocPermission(@RequestParam("roleId") Long roleId,
                                  @RequestParam("permissionIds") List<Long> permissionIds) {
        CommonResult commonResult;
//        List<TesRoleMenu> tesRoleMenu = new ArrayList<TesRoleMenu>();
//        TesRoleMenu tesRoleMenu = new TesRoleMenu();
//        BeanUtils.copyProperties(tesRoleParam, tesRoleMenu);
        System.out.println(permissionIds + "`111222333");
        int count = 0;
        try {
            int count1 = tesRoleService.delRolePermission(roleId);
        } catch (Exception e) {
            System.out.println("删除权限失败");
        }
        for (Long permissionId : permissionIds) {

            count = tesRoleService.insertPermission(roleId, permissionId);
        }

        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

}
