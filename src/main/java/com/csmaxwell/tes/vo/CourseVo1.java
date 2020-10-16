package com.csmaxwell.tes.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseVo1 {

    private String[] columns = new String[0];
    private List<Map<String,Integer>> rows = new ArrayList<Map<String, Integer>>();

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Map<String, Integer>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Integer>> rows) {
        this.rows = rows;
    }
}
