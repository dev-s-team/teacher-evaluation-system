package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesEvaluationService;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "TesEvaluationController", description = "评教管理")
@RestController
@RequestMapping("/evaluation")
public class TesEvaluationController {

    @Autowired
    private TesEvaluationService tesEvaluationService;

    @Autowired
    private TesIndicatorService tesIndicatorService;

    @ApiOperation(value = "发布评教")
    @RequestMapping(value = "/update/{evaluationControlId}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:evaluationControl:update')")
    public CommonResult update(@PathVariable Long evaluationControlId) {

        CommonResult commonResult;

        int count = tesEvaluationService.updateById(evaluationControlId);

        if (count == 1) {
            commonResult = CommonResult.success("该评教发布成功！");
        } else {
            commonResult = CommonResult.failed("该评教已经发布了！");
        }
        return commonResult;
    }

//    @ApiOperation(value = "新增评教表")
//    @RequestMapping(value = "/evaluation", method = RequestMethod.POST)
//    @ResponseBody
////    @PreAuthorize("hasAuthority('pms:evaluationControl:update')")
//    public CommonResult add(@PathVariable Long evaluationControlId) {
//
//        CommonResult commonResult =null;
//
//        List<TesIndicator> indicators = tesIndicatorService.select();
//        if(indicators != null){
//
//        }
//        return commonResult;
//    }

    @ApiOperation(value = "查询评教结果")
    @RequestMapping(value = "/reade", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:evaluationResult:reade')")
    public CommonResult reade() {
        List<TesEvaluation> tesEvaluations = tesEvaluationService.select();
        if (tesEvaluations != null) {

            return CommonResult.success(tesEvaluations);
        } else {
            return CommonResult.failed("查询用户信息失败");
        }
    }
}
