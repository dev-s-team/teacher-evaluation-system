package com.csmaxwell.tes.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseVo {

    private String[] columns = new String[0];
    private List<Map<String,String>> rows = new ArrayList<Map<String, String>>();

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }
}
