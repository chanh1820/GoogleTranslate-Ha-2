package com.example.googletranslate.core.dto;

import java.io.Serializable;
import java.util.List;

public class TranlateResponseLv2DTO implements Serializable {
    private List<TranlateResponseLv3DTO> tranlateResponseLv3DTOList;

    public TranlateResponseLv2DTO() {
    }

    public List<TranlateResponseLv3DTO> getTranlateResponseLv3DTOList() {
        return tranlateResponseLv3DTOList;
    }

    public void setTranlateResponseLv3DTOList(List<TranlateResponseLv3DTO> tranlateResponseLv3DTOList) {
        this.tranlateResponseLv3DTOList = tranlateResponseLv3DTOList;
    }
}
