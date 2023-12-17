package com.example.googletranslate.core.dto;

import java.io.Serializable;
import java.util.List;

public class TranlateResponseDTO implements Serializable {
    private List<TranlateResponseLv2DTO> tranlateResponseLv2DTOList;

    public TranlateResponseDTO() {
    }

    public List<TranlateResponseLv2DTO> getTranlateResponseLv2DTOList() {
        return tranlateResponseLv2DTOList;
    }

    public void setTranlateResponseLv2DTOList(List<TranlateResponseLv2DTO> tranlateResponseLv2DTOList) {
        this.tranlateResponseLv2DTOList = tranlateResponseLv2DTOList;
    }
}
