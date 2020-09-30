package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesIndicator;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesEvaluationControlService;
import com.csmaxwell.tes.service.TesEvaluationService;
import com.csmaxwell.tes.service.TesIndicatorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TesEvaluationServiceImplTest {

    @Autowired
    private TesEvaluationService tesEvaluationService;

    @Autowired
    private TesIndicatorService tesIndicatorService;

    @Autowired
    private TesEvaluationControlService tesEvaluationControlService;

    @Test
    public void list() {
        List<TesEvaluationControl> evlControlList = tesEvaluationControlService.tecList( 1L);

        TesEvaluationControl evalControl = evlControlList.get(0);
        System.out.println(evalControl);

        List<TesEvaluation> evaluationList = tesEvaluationService.teList(evalControl.getId());
        System.out.println(evaluationList);

        TesIndicator indicatorList = null;
        List<TesIndicator> allIndicatorLists = new ArrayList<TesIndicator>();


        for (TesEvaluation tesEvaluation : evaluationList) {

            indicatorList = tesIndicatorService.indicatorList(tesEvaluation.getIndicatorId());
            System.out.println(indicatorList);
            // TesIndicator tesIndicator = indicatorList.get(0);

            allIndicatorLists.add(indicatorList);
            System.out.println(allIndicatorLists);
        }
//        System.out.println(allIndicatorList);
    }
}