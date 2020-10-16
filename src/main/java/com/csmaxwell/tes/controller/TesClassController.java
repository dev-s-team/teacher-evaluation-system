package com.csmaxwell.tes.controller;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesClass;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesClassService;
import com.csmaxwell.tes.vo.CourseVo;
import com.csmaxwell.tes.vo.CourseVo1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * S
 * Created by maxwell on 2020/9/17.
 */
@Api(tags = "TesClassController", description = "班级管理")
@RestController
@RequestMapping("/class")
public class TesClassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesClassController.class);

    @Autowired
    private TesClassService tesClassService;

    @ApiOperation(value = "新增班级")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:class:create')")
    public CommonResult create(@RequestBody TesClass tesClassParam) {
        CommonResult commonResult;
        int count = tesClassService.create(tesClassParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除班级")
    @RequestMapping(value = "/delete/{classId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:class:delete')")
    public CommonResult delete(@PathVariable Long classId) {
        int count = tesClassService.delete(classId);
        if (count ==1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除班级失败");
        }
    }

    @ApiOperation(value = "查询班级列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesClass>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesClass> classList = tesClassService.findAll(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(classList));
    }

    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TesClass>> all() {
        List<TesClass> classList = tesClassService.all();
        return CommonResult.success(classList);
    }

    @ApiOperation(value = "查询班级信息")
    @RequestMapping(value = "/reade/{classId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:class:reade')")
    public CommonResult reade(@RequestParam Long classId) {
        TesClass tesClass = tesClassService.select(classId);
        if (tesClass != null) {
            return CommonResult.success(tesClass);
        } else {
            return CommonResult.failed("查询班级信息失败");
        }
    }

    @ApiOperation(value = "更新班级信息")
    @RequestMapping(value = "/update/{classId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('lms:class:update')")
    public CommonResult update(@PathVariable Long classId ,@RequestBody TesClass tesClass) {
        CommonResult commonResult;
        int count = tesClassService.update(classId,tesClass);
        if (count == 1){
            commonResult=CommonResult.success(null);
        }else {
            commonResult=CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "查询评教班级数量")
    @RequestMapping(value = "/classCount", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult classCount(){
        int count = tesClassService.selectCountClass();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "查询每个班级人数")
    @RequestMapping(value = "/classCountUserAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult classCountUserAll(){
//        查询每个班级的所有人数
        int[] classAllList = tesClassService.classAllUser();
//        查询每个班级号
        int[] classNoList = tesClassService.classAllUserComplete();
        List<Map<String,Integer>> rows = new ArrayList<>();
        for (int i = 0;i<classAllList.length;i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("班级号", classNoList[i]);
            map.put("班级人数", classAllList[i]);
            rows.add(map);
        }
        CourseVo1 courseVo = new CourseVo1();
        String[] columns = new String[]{"班级号","班级人数"};
        courseVo.setColumns(columns);


        courseVo.setRows(rows);
        if (classAllList != null) {
            return CommonResult.success(courseVo);
        } else {
            return CommonResult.failed();
        }

    }
}
