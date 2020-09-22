package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesSemester;
import com.csmaxwell.tes.service.TesSemesterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "TesSemesterController", description = "批次管理")
@RestController
@RequestMapping("/semester")
public class TesSemesterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesSemesterController.class);

    @Autowired
    private TesSemesterService tesSemesterService;

    @ApiOperation(value = "新增批次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:semester:create')")
    public CommonResult create(@RequestBody TesSemester tesSemesterParam) {
        CommonResult commonResult;
        int count = tesSemesterService.create(tesSemesterParam);
        if (count == 1) {
            commonResult = CommonResult.success(null);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

}
