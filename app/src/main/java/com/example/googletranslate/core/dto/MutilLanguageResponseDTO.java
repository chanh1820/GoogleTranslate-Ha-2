package com.example.googletranslate.core.dto;

public class MutilLanguageResponseDTO {

    private String languageCode;

    private String result;

    public MutilLanguageResponseDTO() {
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
