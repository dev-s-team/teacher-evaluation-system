package com.csmaxwell.tes.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesMenu;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesRole;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.dto.TesUserLoginParam;
import com.csmaxwell.tes.service.TesRoleService;
import com.csmaxwell.tes.service.TesUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private TesRoleService tesRoleService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(TesUserController.class);

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
        String token = tesUserService.login(tesUserLoginParam.getNo(), tesUserLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getUserInfo(Principal principal) {
        System.out.println("哈哈哈哈");
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String no = principal.getName();
        System.out.println("no是: " + no);
        TesUser tesUser = tesUserService.getUserByNo(no);
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", tesUser.getUsername());
        List<TesMenu> menus = tesRoleService.getMenuList(tesUser.getId());
        data.put("menus", menus);
        TesRole tesRole = tesRoleService.findById(tesUser.getRoleId());
        // if(CollUtil.isNotEmpty(roleList)){
        //     List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
        //     data.put("roles",roles);
        // }
        data.put("roles", tesRole.getName());
        System.out.println("获取用户信息");
        return CommonResult.success(data);
    }

    @ApiOperation(value = "获取用户所有权限")
    @RequestMapping(value = "/permission/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesPermission>> getPermissionList(@PathVariable Long userId) {
        List<TesPermission> permissionList = tesUserService.getPermissionList(userId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:user:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int count = tesUserService.delete(id);
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

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesUser> userList = tesUserService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @ApiOperation(value = "根据id获取角色")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TesRole> findRoleById(@PathVariable("id") Long id) {
        return CommonResult.success(tesUserService.findRoleById(id));
    }

    @ApiOperation(value = "根据id更新用户角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("userId") Long userId,
                                   @RequestParam("roleId") Long roleId) {
        int count = tesUserService.updateRole(userId, roleId);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "导入用户")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        String fileName = excelFile.getOriginalFilename();
        // 上传文件为空
        if (StringUtils.isEmpty(excelFile)) {
            return CommonResult.failed("不能上传空文件");
        }
        // 限制上传大小
        if (excelFile.getSize() > 1024 * 1024 * 40) {
            return CommonResult.failed("文件过大");
        }
        // 上传文件格式不正确
        if (fileName.lastIndexOf(".") != -1 && !".xlsx".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            return CommonResult.failed("文件格式不正确");
        }
        // 读取数据
        ExcelReader reader = ExcelUtil.getReader(excelFile.getInputStream(), 0);
        List<Map<String, Object>> maps = reader.readAll();
        for (Map<String, Object> map : maps) {
            for (String key : map.keySet()) {
                System.out.println(key);
                System.out.println(map.get(key));
            }
        }

        return null;
    }
}
