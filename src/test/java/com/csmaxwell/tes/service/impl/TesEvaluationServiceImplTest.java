package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.domain.TesEvaluation;
import com.csmaxwell.tes.domain.TesEvaluationControl;
import com.csmaxwell.tes.domain.TesIndicator;
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

//        System.out.println(allIndicatorList);
    }

    @Test
    public void updateById() {
        int count = tesEvaluationService.updateById(7L);
        System.out.println(count);
    }
}