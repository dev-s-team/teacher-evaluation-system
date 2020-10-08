package com.csmaxwell.tes.common.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 评教选项，每项对应分值
 * Created by maxwell on 2020/10/7.
 */
public class EvalOption {
    public static final List<BigDecimal> options = Arrays.asList(
            new BigDecimal("1.0"),
            new BigDecimal("0.8"),
            new BigDecimal("0.6"),
            new BigDecimal("0.2"));
}
