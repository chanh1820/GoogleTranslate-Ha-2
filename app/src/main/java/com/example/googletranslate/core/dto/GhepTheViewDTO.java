package com.example.googletranslate.core.dto;

import android.widget.TextView;

import java.util.List;

public class GhepTheViewDTO {
    private Integer unitId;

    private List<TextView> manhGhepViewList;
    private List<ManhGhepDTO> manhGhepDTOS;
    public GhepTheViewDTO() {
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public List<TextView> getManhGhepViewList() {
        return manhGhepViewList;
    }

    public void setManhGhepViewList(List<TextView> manhGhepViewList) {
        this.manhGhepViewList = manhGhepViewList;
    }

}
