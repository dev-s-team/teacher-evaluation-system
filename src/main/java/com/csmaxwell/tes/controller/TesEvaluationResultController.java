package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.common.constant.EvalOption;
import com.csmaxwell.tes.domain.TesEvaluationResult;
import com.csmaxwell.tes.service.TesEvaluationResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 评教结果管理
 * Created by maxwell on 2020/9/26.
 */
@Api(tags = "TesEvaluationResultController", description = "评教结果管理")
@RestController
@RequestMapping("/evalResult")
public class TesEvaluationResultController {

    @Autowired
    private TesEvaluationResultService tesEvaluationResultService;

    @ApiOperation("新增评教结果")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody TesEvaluationResult tesEvaluationResult) {
        CommonResult commonResult;
        int count = tesEvaluationResultService.create(tesEvaluationResult);
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
    public CommonResult<CommonPage<TesEvaluationResult>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesEvaluationResult> resultList = tesEvaluationResultService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resultList));
    }

    @ApiOperation("根据评教人id查询")
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TesEvaluationResult>> select(@PathVariable("userId") Long userId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TesEvaluationResult> list = tesEvaluationResultService.select(userId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("提交评教结果")
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult commitEval(@RequestParam("userId") Long userId,
                                   @RequestParam("roleId") Long roleId,
                                   @RequestParam("targetId") Long targetId,
                                   @RequestParam("courseId") Long courseId,
                                   @RequestParam("radios") List<String> radios,
                                   @RequestParam("weights") List<String> weights) {
        int count = tesEvaluationResultService.commit(userId, roleId, targetId, courseId, radios, weights);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }

    }



}
