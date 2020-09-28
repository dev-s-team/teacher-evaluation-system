package com.csmaxwell.tes.controller;


import com.csmaxwell.tes.common.api.CommonPage;
import com.csmaxwell.tes.common.api.CommonResult;
import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.service.TesEvaluationControlService;
import com.csmaxwell.tes.service.TesEvaluationService;
import com.csmaxwell.tes.service.TesIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "TesEvaluationController", description = "评教管理")
@RestController
@RequestMapping("/evaluation")
public class TesEvaluationController {

    @Autowired
    private TesEvaluationService tesEvaluationService;

    @Autowired
    private TesEvaluationControlService tesEvaluationControlService;

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

    @ApiOperation(value = "开始评教")
    @RequestMapping(value = "/startEvaluation", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:evaluation:startEvaluation')")
    public CommonResult<List<TesIndicator>> list(@PathVariable(value = "user_id", required = false) Long userId,
                                                     @PathVariable(value = "role_id", required = false) Long roleId,
                                                     @PathVariable(value = "target_id", required = false) Long targetId,
                                                     @PathVariable(value = "course_id", required = false) Long courseId,
                                                     @PathVariable(value = "semester_id", required = false) Long semesterId,
                                                     @RequestBody TesEvaluationControl tesEvaluationControl
                                                    ) {

        List<TesEvaluationControl> evlControlList = tesEvaluationControlService.tecList(semesterId);

        TesEvaluationControl evalControl = evlControlList.get(0);
        System.out.println(evalControl);

        List<TesEvaluation> evaluationList = tesEvaluationService.teList(evalControl.getId());
        System.out.println(evaluationList);

        TesIndicator indicatorList = null;
        List<TesIndicator> allIndicatorLists = new ArrayList<TesIndicator>();


        for (TesEvaluation tesEvaluation : evaluationList) {

            indicatorList = tesIndicatorService.indicatorList(tesEvaluation.getIndicatorId());
            System.out.println(indicatorList);

            allIndicatorLists.add(indicatorList);
            System.out.println(allIndicatorLists);
        }
        return CommonResult.success(allIndicatorLists);
    }


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
