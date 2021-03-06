package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.common.util.POIUtil;
import com.csmaxwell.tes.domain.*;
import com.csmaxwell.tes.dto.TesUserLoginParam;
import com.csmaxwell.tes.service.TesDepartmentService;
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
import java.util.*;

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
    @Autowired
    private TesDepartmentService tesDepartmentService;

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
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String no = principal.getName();

        TesUser tesUser = tesUserService.getUserByNo(no);
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", tesUser.getUsername());
        List<TesMenu> menus = tesRoleService.getMenuList(tesUser.getId());
        data.put("menus", menus);
        TesRole tesRole = tesRoleService.findById(tesUser.getRoleId());

        data.put("roles", tesRole.getName());

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
    @PreAuthorize("hasAuthority('ums:user:create')")
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
    @PreAuthorize("hasAuthority('ums:user:delete')")
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
    @PreAuthorize("hasAuthority('ums:user:reade')")
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
    @PreAuthorize("hasAuthority('ums:user:update')")
    public CommonResult update(@PathVariable Long userId ,@RequestBody TesUser tesUser) {
        int count = tesUserService.update(userId,tesUser);
        if (count == 1) {
            return CommonResult.success("更新用户信息成功");
        } else {
            return CommonResult.failed("更新用户信息失败");
        }
    }

    @ApiOperation("更新用户状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('ums:user:update')")
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        int count = tesUserService.updateStatus(id, status);
        if (count == 1) {
            return CommonResult.success("更新状态成功");
        } else {
            return CommonResult.failed("更新状态失败");
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

        List<String[]> list = POIUtil.readExcel(excelFile);

        List<TesUser> data = new ArrayList<>();

        // 获取角色列表
        List<TesRole> roleList = tesRoleService.selectAll();
        Map<String, Long> roleMap = new HashMap<>();
        for (TesRole tesRole : roleList) {
            roleMap.put(tesRole.getName(), tesRole.getId());
        }

        // 获取院系列表
        List<TesDepartment> deptList = tesDepartmentService.all();
        Map<String, String> deptMap = new HashMap<>();
        for (TesDepartment dept : deptList) {
            deptMap.put(dept.getName(), dept.getNo());
        }

        for (String[] strings : list) {
            String no = strings[0];
            String username = strings[1];
            String gender = strings[2];
            String roleKey = strings[3];
            String classNo = strings[4];
            String deptKey = strings[5];
            Integer status = Integer.valueOf(strings[6]);

            TesUser tesUser = new TesUser();
            tesUser.setNo(no);
            tesUser.setUsername(username);
            tesUser.setGender(gender);
            tesUser.setRoleId(roleMap.get(roleKey));
            tesUser.setClassNo(classNo);
            tesUser.setDeptNo(deptMap.get(deptKey));
            tesUser.setStatus(status);
            data.add(tesUser);
        }

        for (TesUser datum : data) {
            System.out.println(datum);
        }
        List<TesUser> tesUsers = tesUserService.findAll();
        int count = 0;
        for(TesUser tesUser: tesUsers){
            count = 0;
            for(TesUser tesUser1 : data){
                String no = tesUser.getNo();
                if(!no.equals(tesUser1.getNo())){
                    count = count + 1;
                    continue;

                }

            }
        }
        System.out.println(count + "count");
        System.out.println(data.size() + "size");
        if(count==data.size()){
            tesUserService.add(data);
            return CommonResult.success("导入成功");
        }else {
            return CommonResult.failed("导入失败");
        }



    }

    @ApiOperation(value = "查询学生人数")
    @RequestMapping(value = "/selectStudent", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult selectStudent( ){
        int count = tesUserService.countStudent();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "查询教师人数")
    @RequestMapping(value = "/selectTeacher", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult selectTeacher( ){
        int count = tesUserService.countTeacher();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "查询班级数")
    @RequestMapping(value = "/selectClass", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult selectClass( ){
        int count = tesUserService.countClass();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "查询学院数")
    @RequestMapping(value = "/selectDepartment", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult selectDepartment( ){
        int count = tesUserService.countDepartment();
        return CommonResult.success(count);
    }

}
