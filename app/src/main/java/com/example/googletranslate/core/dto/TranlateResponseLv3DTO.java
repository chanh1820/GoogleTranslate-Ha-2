package com.example.googletranslate.core.dto;

import java.io.Serializable;
import java.util.List;

public class TranlateResponseLv3DTO implements Serializable {
    private List<String> response;

    public TranlateResponseLv3DTO() {
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }
}
