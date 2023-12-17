package com.example.googletranslate.core.dto;

public class ItemResultDTO {
    private boolean isResult;

    private String content;

    public ItemResultDTO() {
    }

    public ItemResultDTO(boolean isResult, String content) {
        this.isResult = isResult;
        this.content = content;
    }

    public boolean isResult() {
        return isResult;
    }

    public void setResult(boolean result) {
        isResult = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
